package com.zakin.readerzmultiplatform

import com.zakin.readerzmultiplatform.models.Chapter
import com.zakin.readerzmultiplatform.models.Manga
import com.zakin.readerzmultiplatform.models.MangaList
import com.zakin.readerzmultiplatform.models.Page
import com.zakin.readerzmultiplatform.models.enums.Badges
import com.zakin.readerzmultiplatform.models.enums.Tags
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


actual class ScrapService actual constructor() {

    actual fun getMangaList(url: String): MangaList {
        val mangaList = MangaList()

        if (url.isNotEmpty() && url != "undefined")
        {
            val listMangaUrl = "$url/changeMangaList?type=text"

            val connect = Jsoup.connect(listMangaUrl)
                .ignoreHttpErrors(true)
                .timeout(0)

            val doc = connect.get()

            val elems = doc.select("li > a")

            for (elem in elems) {
                val link = (if (elem.hasAttr("href")) elem.attr("href") else "").trim { it <= ' ' }
                val name = elem.select("h6").html()

                val manga = Manga(name = name, link = link)

                mangaList.mangas.add(manga)
            }

            mangaList.count = elems.size
        }

        return mangaList
    }

    actual fun getManga(url: String): Manga {
        val manga = Manga()

        if (url.isNotEmpty() && url != "undefined")
        {
            val doc = Jsoup.connect(url)
                .ignoreHttpErrors(true)
                .timeout(0).get()

            val listItemsName = doc.select("dt")
            val listItems = doc.select("dd")

            val map = HashMap<String, Element>()

            var index = 0;
            for (elem in listItemsName) {
                map.put(elem.html(), listItems[index]);
                index++;
            }

            if (map.containsKey("Statut")) {
                val tagNode = map.get("Statut")?.select("span")
                if (tagNode != null) {
                    manga.tag = enumValueOf<Tags>(tagNode.html().uppercase(Locale.getDefault()))
                }
            }

            if (map.containsKey("Auteur(s)")) {
                val authorNode = map.get("Auteur(s)")?.select("a")
                if (authorNode != null) {
                    manga.author = authorNode.html()
                }
            }

            if (map.containsKey("Date de sortie")) {
                val releaseYearNode = map.get("Date de sortie")
                if (releaseYearNode != null) {
                    manga.author = releaseYearNode.html()
                }
            }

            val resumeInfo = doc.select("html > body > div[1] > div > div[1] > div > div[2] > div > div > p")
            if (!resumeInfo.isEmpty() && resumeInfo.html() != "")
            {
                manga.resume = resumeInfo.html()
            }

            val cover = doc.select("div.boxed > img");
            if (!cover.isEmpty()) {
                manga.coverLink = cover.attr("src")
            }

            val elems = doc.select("ul.chapters > li")
            for (elem in elems) {
                val chapter = Chapter()

                val aLink = elem.select("h5 > a")
                val subName = elem.select("h5 > em")
                val badgeString = elem.select("h5 > span")

                if (!aLink.isEmpty()) {
                    val link = getHtmlLink(aLink.outerHtml())
                    val number = link.substringAfterLast('/')

                    chapter.name = aLink.html()
                    chapter.link = link
                    chapter.number = number
                }

                if (!subName.isEmpty() && subName.html() != "") {
                    chapter.subName = subName.html()
                }

                if (!badgeString.isEmpty() && badgeString.html() != "") {
                    chapter.badge = enumValueOf<Badges>(badgeString.html().uppercase(Locale.getDefault()))
                }

                val dateElement = elem.select("div > div")

                if(dateElement.isNotEmpty()) {
                    val formatter = DateTimeFormatter.ofPattern("dd MMM. yyyy", Locale.ENGLISH)
                    chapter.addedDate = LocalDate.parse(dateElement.text(), formatter).format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH))
                }

                manga.chapters.add(chapter);
            }
        }

        return manga
    }

    actual fun getChapter(url: String): Chapter {
        val chapter = Chapter()

        if (url.isNotEmpty() && url != "undefined")
        {
            val doc = Jsoup.connect(url)
                .ignoreHttpErrors(true)
                .timeout(0).get()

            val pages = doc.select("div#all > img")

            pages.forEachIndexed { index, page ->
                var src = page.attr("data-src")

                if(src.isNullOrBlank()) {
                    src = page.attr("src")
                }

                val name = page.attr("alt")

                chapter.pages.add(Page(index + 1, name, src))
            }
        }

        return chapter
    }

    actual fun getPage(url: String): ByteArray {

        if (url.isNotEmpty() && url != "undefined")
        {
            val XSRFTOKEN = "XSRF-TOKEN=eyJpdiI6IjZGcGhoeHRZQUNNVzZDUDVcL2owb3BRPT0iLCJ2YWx1ZSI6IllpeDB6SnBEQ2k0MThlc0VMajJyY2NNT0hsdEt0YjAwUjdhRXNYTXVQa3dVNTdibnpPMzV4dmlPNFZ2bENXN3ZMWXY0N0JHTUxjYzhlRHBKSCtkZVZ3PT0iLCJtYWMiOiI3OTk4YWNiZjI2M2M0ZWUwNmQxNTdhNTZiZjY1ZDc3ZDNmZWFkODE3OGQzZTMzZjcwNjAxMGViNTk1ZWYwYTIxIn0%3D"
            val LARAVEL = "laravel_session=eyJpdiI6Im9SRndOcSt6NUdrK2YzMCttMm0zcFE9PSIsInZhbHVlIjoidmFcL1JKQmp3NGlETzlLSGtXXC9FZWNpVEt5dmJ4YUlqXC9sTEdtQ0FvUmNIalllamJEb1FpYnVISkNJZXowQ0VhUm5xMjRHNDFKUFlPTGljb1pUYlhKamc9PSIsIm1hYyI6IjRkOTU0OThhMmNmNDk3NTEwMzYxNjAwYWM4NTc4ZmJlYjc0YjA2MzNkM2ExOGZkYTExODI2NzM2MzMzYzIwYWQifQ%3D%3D"
            val CFBM = "__cf_bm=TC7Lge.9K40AnA3Dvyw4s0y9bjMqOxmo9wV55zomu6Y-1660770640-0-AcDolHCMAp4oUhn3t/J1E1zKHa5eUIFfuLvXWoS1jeEkFCUB1wNJRN4par5tj7xbMCjnC/H1WPP57wq7JVHvk7dBmh4UALXkfUA/EcZ62Y0YseQ5trhjRMW44HhMMTAiDg=="

            val list = listOf(XSRFTOKEN, LARAVEL, CFBM)

            val itemLink = (if (url.contains("http")) url else url.replace("//", "https://")).trim();
            val imgConnection = URL(itemLink).openConnection() as HttpURLConnection
            imgConnection.setRequestProperty("COOKIES", list.joinToString(";"))
            imgConnection.connect()
            val responseCode = imgConnection.responseCode
            return if (responseCode == HttpURLConnection.HTTP_OK) {
                imgConnection.inputStream.use { it.readBytes() }
            } else {
                ByteArray(0)
            }
        } else {
            return ByteArray(0)
        }
    }

    fun getHtmlLink(link: String): String {
        val rx = Regex("<a href=\\\"(.*)\\\"")
        val match = rx.find(link)

        return match?.groups?.get(1)?.value ?: ""
    }

}