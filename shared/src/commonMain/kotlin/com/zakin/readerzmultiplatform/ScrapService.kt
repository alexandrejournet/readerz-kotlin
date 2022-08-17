package com.zakin.readerzmultiplatform

import com.zakin.readerzmultiplatform.models.Chapter
import com.zakin.readerzmultiplatform.models.Manga
import com.zakin.readerzmultiplatform.models.MangaList
import com.zakin.readerzmultiplatform.models.Page

expect class ScrapService() {
    fun getMangaList(url: String): MangaList
    fun getManga(url: String): Manga
    fun getChapter(url: String): Chapter
    fun getPage(url: String): ByteArray
}