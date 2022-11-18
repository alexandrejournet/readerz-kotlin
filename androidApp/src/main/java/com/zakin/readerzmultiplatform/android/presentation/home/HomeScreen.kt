package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.tabHighlight
import com.zakin.readerzmultiplatform.android.ui.composable.BibliothequeScreen
import com.zakin.readerzmultiplatform.android.ui.composable.SitesScreen
import com.zakin.readerzmultiplatform.android.ui.composable.ParametreScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabWithPager(router: Router) {
    val tabData = listOf(
        Icons.Rounded.Book,
        Icons.Rounded.Explore,
        Icons.Rounded.MoreHoriz,
    )

    val iconNotSelected = listOf(
        Icons.Outlined.Book,
        Icons.Outlined.Explore,
        Icons.Outlined.MoreHoriz,
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    var tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    ReaderzMultiplatformTheme {
        Scaffold(
            bottomBar = {
                TabRow(
                    selectedTabIndex = tabIndex,
                    Modifier.height(56.dp)
                ) {
                    tabData.forEachIndexed { index, icon ->
                        Tab(selected = tabIndex == index, onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                            tabIndex = index
                        }, icon = {
                            Icon(
                                imageVector = if (tabIndex == index) icon else iconNotSelected[index],
                                contentDescription = null,
                                tint = if (tabIndex == index) tabHighlight else MaterialTheme.colors.primaryVariant,
                            )
                        })
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.background
        ) {
            HorizontalPager(
                state = pagerState,
                count = 3
            ) { index ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (index) {
                        0 -> BibliothequeScreen(router)
                        1 -> SitesScreen(router)
                        2 -> ParametreScreen(router)
                    }
                }
            }
        }
    }
}
