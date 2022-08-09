package com.zakin.readerzmultiplatform.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zakin.readerzmultiplatform.android.routing.Router
import com.zakin.readerzmultiplatform.android.ui.composable.BibliothequeView
import com.zakin.readerzmultiplatform.android.ui.composable.ExplorerView
import com.zakin.readerzmultiplatform.android.ui.fragments.SettingsFragment
import com.zakin.readerzmultiplatform.android.ui.services.ScanService
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
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    var tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        bottomBar =  {
            TabRow(selectedTabIndex = tabIndex) {
                tabData.forEachIndexed { index, icon ->
                    Tab(selected = tabIndex == index, onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                        tabIndex = index
                    }, icon = {
                        Icon(imageVector = icon,
                            contentDescription = null,
                            tint = if (tabIndex == index) MaterialTheme.colors.secondary else MaterialTheme.colors.secondaryVariant,  )
                    })
                }
            }
        }
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
                    0 -> BibliothequeView(router)
                    1 -> ExplorerView(router)
                    2 -> SettingsFragment()
                }
            }
        }
    }
}
