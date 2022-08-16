package com.zakin.readerzmultiplatform.android.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zakin.readerzmultiplatform.android.domain.models.Site
import com.zakin.readerzmultiplatform.models.Manga

class ScanService: ViewModel() {

    var site by mutableStateOf(Site())
        private set

    var manga by mutableStateOf(Manga())
        private set

    fun addSite(selectedSite: Site) {
        site = selectedSite
    }

    fun addManga(selectedManga: Manga) {
        manga = selectedManga
    }
}