package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.data.ScanService
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.items.SiteMangaListItem
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar.SiteToolbar
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.MangaList
import java.util.concurrent.Executors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SiteScreen(router: Router, scanService: ScanService) {

    var mangaList by remember { mutableStateOf(MangaList()) }

    val executor = Executors.newSingleThreadExecutor()
    executor.execute {
        mangaList = ScrapService().getMangaList(scanService.site.url)
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
                Crossfade(targetState = mangaList) { it1 ->
                    if(it1.mangas.isEmpty()) {
                        Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
                            Text("Scrap en cours...", fontSize = 14.sp, color = MaterialTheme.colors.secondary, textAlign = TextAlign.Center,)
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(
                                items = mangaList.mangas,
                                itemContent = {
                                    SiteMangaListItem(manga = it, router = router)
                                })
                        }
                    }
                }
            }
        }
    }
}