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
import com.zakin.readerzmultiplatform.android.models.data.MangaViewModel
import com.zakin.readerzmultiplatform.android.routing.Router
import com.zakin.readerzmultiplatform.android.ui.composable.items.BibliothequeListItem
import com.zakin.readerzmultiplatform.android.ui.composable.toolbar.SiteToolbar
import com.zakin.readerzmultiplatform.android.ui.services.ScanService
import com.zakin.readerzmultiplatform.android.ui.theme.ReaderzMultiplatformTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SiteView(router: Router, scanService: ScanService) {
    // ArrayList of class ItemsViewModel
    val data = ArrayList<MangaViewModel>()

    // This loop will create 20 Views containing
    // the image with the count of view
    for (i in 1..3) {
        data.add(MangaViewModel("Item $i", i))
    }

    ReaderzMultiplatformTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    scanService.site?.let {
                        SiteToolbar(
                            siteName = it.name,
                            onClickBack = { router.goBack() }
                        )
                    }
                }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = data,
                        itemContent = {
                            BibliothequeListItem(manga = it)
                        })
                }
            }
        }
    }
}