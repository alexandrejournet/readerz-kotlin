package com.zakin.readerzmultiplatform.android.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.domain.data.MangaViewModel
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.items.BibliothequeListItem
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar.GenericToolbar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BibliothequeScreen(router: Router) {
    // ArrayList of class ItemsViewModel
    val data = ArrayList<MangaViewModel>()

    // This loop will create 20 Views containing
    // the image with the count of view
    for (i in 1..3) {
        data.add(MangaViewModel("Item $i", i))
    }

    Scaffold(
        topBar = { GenericToolbar("Bibliothèque") }
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