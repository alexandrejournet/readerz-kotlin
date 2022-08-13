package com.zakin.readerzmultiplatform

import com.zakin.readerzmultiplatform.models.Chapter
import com.zakin.readerzmultiplatform.models.Manga
import com.zakin.readerzmultiplatform.models.MangaList
import com.zakin.readerzmultiplatform.models.Page
import com.zakin.readerzmultiplatform.models.enums.Badges
import com.zakin.readerzmultiplatform.models.enums.Tags
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap


actual class ScrapService actual constructor() {

    actual fun getMangaList(url: String): MangaList {
        val mangaList = MangaList()

        if (url.isNotEmpty() && url != "undefined")
        {
            val listMangaUrl = "$url/changeMangaList?type=text"

            val doc = Jsoup.connect(listMangaUrl).get()
            val elems = doc.select("li > a")

            for (elem in elems) {
                val link = (if (elem.hasAttr("href")) elem.attr("href") else "").trim { it <= ' ' }
                val name = elem.select("h6").html()

                val manga = Manga(name = name, link = link)

                mangaList.mangas?.add(manga)
            }

            mangaList.count = elems.size
        }

        return mangaList
    }

    actual fun getManga(url: String): Manga {
        val manga = Manga()

        if (url.isNotEmpty() && url != "undefined")
        {
            val doc = Jsoup.connect(url).get()

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
                    chapter.addedDate = LocalDate.parse(dateElement.text(), formatter)
                }

                manga.chapters?.add(chapter);
            }
        }

        return manga
    }

    actual fun getChapter(url: String): Chapter {
        val chapter = Chapter()

        if (url.isNotEmpty() && url != "undefined")
        {
            val doc = Jsoup.connect(url).get()

            val pages = doc.select("div#all > img")

            pages.forEachIndexed { index, page ->
                var src = page.attr("data-src")

                if(src.isNullOrBlank()) {
                    src = page.attr("src")
                }

                val name = page.attr("alt")

                chapter.pages?.add(Page(index + 1, name, src))
            }
        }

        return chapter
    }

    actual fun getPage(baseUrl: String, url: String): Page {
        val page = Page()

        if (url.isNotEmpty() && url != "undefined" && baseUrl.isNotEmpty() && baseUrl != "undefined")
        {

        }

        return page
    }

    fun getHtmlLink(link: String): String {
        val rx = Regex("<a href=\\\"([^\\\"]*)\\\"")
        val match = rx.find(link)

        return match?.value ?: ""
    }

}