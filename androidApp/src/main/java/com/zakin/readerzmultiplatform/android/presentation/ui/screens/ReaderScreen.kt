package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.data.ScanService
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar.SiteToolbar
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.Chapter
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReaderScreen(router: Router, scanService: ScanService) {

    var chapter by remember { mutableStateOf(Chapter()) }

    val executor = Executors.newSingleThreadExecutor()
    executor.execute {
        val temp = ScrapService().getChapter(scanService.chapter.link)
        chapter = scanService.chapter
        chapter.pages = temp.pages
    }

    var pageIndex = 1;
    var showToolbar = true;

    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    var currentPage = pagerState.currentPage

    suspend fun next() {
        pagerState.animateScrollToPage(currentPage++)
    }

    suspend fun previous() {
        pagerState.animateScrollToPage(currentPage--)
    }

    fun showHideBar() {
        showToolbar = !showToolbar
    }

    ReaderzMultiplatformTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.primaryVariant
        ) {
            Crossfade(targetState = chapter) { it1 ->
                if (it1.pages.isEmpty()) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)) {
                        Text("Chargement du chapitre en cours...", fontSize = 14.sp, color = MaterialTheme.colors.secondary, textAlign = TextAlign.Center,)
                    }
                } else {
                    Scaffold(
                        topBar = {
                            if (showToolbar) {
                                SiteToolbar(
                                    siteName = scanService.chapter.name,
                                    onClickBack = { router.goBack() },
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        },
                        bottomBar = {
                            Text(text="${currentPage+1}/${it1.pages.size}", fontSize = 12.sp, color = MaterialTheme.colors.secondary, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        }) {
                        HorizontalPager(
                            count = it1.pages.size,
                            state = pagerState) { currentPage ->
                            Surface(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = MaterialTheme.colors.primaryVariant)) {

                                PageScreen(it1.pages[currentPage])
                            }
                        }
                    }
                }
            }
        }
    }
}
