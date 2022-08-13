package com.zakin.readerzmultiplatform.android.ui.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zakin.readerzmultiplatform.android.models.Site

class ScanService: ViewModel() {

    var site by mutableStateOf(Site())
        private set

    fun addSite(selectedSite: Site) {
        site = selectedSite
    }
}