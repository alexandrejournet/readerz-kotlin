package com.zakin.readerzmultiplatform.android.data

import com.zakin.readerzmultiplatform.models.Manga

data class MangaSearchModelState(
    val searchText: String = "",
    val mangas: List<Manga> = arrayListOf(),
    val showProgressBar: Boolean = false
) {

    companion object {
        val Empty = MangaSearchModelState()
    }

}