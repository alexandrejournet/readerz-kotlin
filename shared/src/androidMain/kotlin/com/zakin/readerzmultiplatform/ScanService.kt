package com.zakin.readerzmultiplatform

import com.zakin.readerzmultiplatform.models.Chapter
import com.zakin.readerzmultiplatform.models.Manga
import com.zakin.readerzmultiplatform.models.MangaList
import com.zakin.readerzmultiplatform.models.Page
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException


class ScanService {

    fun getMangaList(url: String): MangaList {
        val mangaList = MangaList()

        val listMangaUrl = "$url/changeMangaList?type=text"

        val doc = Jsoup.connect(listMangaUrl).get()
        val elems = doc.select("li > a")

        for (elem in elems) {
            val link = (if (elem.hasAttr("href")) elem.attr("href") else "").trim { it <= ' ' }
            val name = elem.select("h6").html()

            val manga = Manga(name = name, link = link)

            mangaList.mangas?.add(manga)
        }

        return mangaList
    }

    fun getManga(url: String): Manga {
        val manga = Manga()
        val doc = Jsoup.connect(url).get()

        val list = doc.select("//ul[@class='chapters'] > li")

        return manga
    }


    /*fun parseAllChapters(url: String?): List<Chapter> {
        val entries: MutableList<Chapter> = ArrayList()
        val doc = Jsoup.connect(url).get()
        val elems = doc.select("li > h5 > a")
        elems.forEach(Consumer { element: Element ->
            val chapterUrl = (if (element.hasAttr("href")) element.attr("href") else "").trim { it <= ' ' }
            val name = element.html()
            val chapterNumber = chapterUrl.substring(chapterUrl.lastIndexOf('/') + 1)
            val chapter = Chapter(chapterNumber.toFloat(), name, chapterUrl, ArrayList())
            entries.add(chapter)
        })
        return entries
    }

    @Throws(IOException::class)
    fun parseManga(url: String?): Chapter {
        val chapter = Chapter()
        chapter.pages = ArrayList()
        val doc = Jsoup.connect(url).get()
        val elems = doc.select("#all > .img-responsive")
        var i = 1
        for (element in elems) {
            val imgUrl =
                (if (element.hasAttr("data-src")) element.attr("abs:data-src") else element.attr("abs:src")).trim { it <= ' ' }
            val chapterName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1)
            val page = Page(i, chapterName, imgUrl)
            chapter.pages.add(page)
            i++
        }
        return chapter
    }*/



    fun getChapter(url: String): Chapter? {
        val chapter: Chapter? = null

        return chapter
    }

    fun getPage(baseUrl: String, url: String): Page? {
        val page: Page? = null

        return page
    }
}