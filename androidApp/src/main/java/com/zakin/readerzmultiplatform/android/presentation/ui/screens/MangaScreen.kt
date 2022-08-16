package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.data.ScanService
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.MangaChapters
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.MangaInfo
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar.SiteToolbar
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.Manga
import java.util.concurrent.Executors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MangaScreen(router: Router, scanService: ScanService) {

    var manga by remember { mutableStateOf(Manga()) }

    val executor = Executors.newSingleThreadExecutor()
    executor.execute {
        manga = ScrapService().getManga(scanService.manga.link)
        manga.name = scanService.manga.name
    }

    ReaderzMultiplatformTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    SiteToolbar(
                        siteName = "",
                        onClickBack = { router.goBack() }
                    )
                }
            ) {
                Crossfade(targetState = manga) { it1 ->
                    if (it1.name == "") {
                        Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
                            Text("Récupération du manga en cours...", fontSize = 14.sp, color = MaterialTheme.colors.secondary, textAlign = TextAlign.Center,)
                        }
                    } else {
                        Column {
                            MangaInfo(manga = manga)
                            MangaChapters(manga = manga, router = router)
                        }
                    }
                }
            }
        }
    }
}