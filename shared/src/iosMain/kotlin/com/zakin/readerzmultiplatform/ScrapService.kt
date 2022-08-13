package com.zakin.readerzmultiplatform

import com.zakin.readerzmultiplatform.models.Chapter
import com.zakin.readerzmultiplatform.models.Manga
import com.zakin.readerzmultiplatform.models.MangaList
import com.zakin.readerzmultiplatform.models.Page

actual class ScrapService {
    actual fun getMangaList(url: String): MangaList {
        TODO("Not yet implemented")
    }

    actual fun getManga(url: String): Manga {
        TODO("Not yet implemented")
    }

    actual fun getChapter(url: String): Chapter {
        TODO("Not yet implemented")
    }

    actual fun getPage(
        baseUrl: String,
        url: String
    ): Page {
        TODO("Not yet implemented")
    }
}