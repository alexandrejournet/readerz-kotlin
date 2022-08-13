package com.zakin.readerzmultiplatform.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.android.routing.Router
import com.zakin.readerzmultiplatform.android.ui.composable.items.SiteMangaListItem
import com.zakin.readerzmultiplatform.android.ui.composable.toolbar.SiteToolbar
import com.zakin.readerzmultiplatform.android.ui.services.ScanService
import com.zakin.readerzmultiplatform.android.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.MangaList
import kotlinx.coroutines.*
suspend fun getMangaList(url: String): MangaList {
    return ScrapService().getMangaList(url)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SiteView(router: Router, scanService: ScanService) {
    var mangaList: LiveData<MangaList> = MutableLiveData()

    mangaList = liveData {
        val data = getMangaList(scanService.site.url)
        emit(data)
    }

    ReaderzMultiplatformTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    SiteToolbar(
                        siteName = scanService.site.name,
                        onClickBack = { router.goBack() }
                    )
                }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = mangaList.mangas,
                        itemContent = {
                            SiteMangaListItem(manga = it)
                        })
                }

            }
        }
    }
}