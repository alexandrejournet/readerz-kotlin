package com.zakin.readerzmultiplatform.android.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.models.Manga
import com.zakin.readerzmultiplatform.models.MangaList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import java.util.concurrent.Executors


class MangaSearchViewModel(private val scanService: ScanService) : ViewModel() {
    private var allMangas: MangaList = MangaList()
    private val searchText: MutableStateFlow<String> = MutableStateFlow("")
    private var matchedMangas: MutableStateFlow<List<Manga>> = MutableStateFlow(emptyList())

    val mangaSearchModelState = combine(
        searchText,
        matchedMangas
    ) {
            text, matchedMangas ->

        MangaSearchModelState(
            text,
            matchedMangas,
        )
    }

    init {
        retrieveUsers()
    }

    fun retrieveUsers() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            allMangas = ScrapService().getMangaList(scanService.site.url)
            matchedMangas.value = allMangas.mangas
        }
    }

    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matchedMangas.value = allMangas.mangas
            return
        }
        val mangasFromSearch = allMangas.mangas.filter { x ->
            x.name.contains(changedSearchText, true)
        }
        matchedMangas.value = mangasFromSearch
    }

    fun onClearClick() {
        searchText.value = ""
        matchedMangas.value = allMangas.mangas
    }

    class Factory(val scanService: ScanService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = MangaSearchViewModel(scanService) as T
    }
}

