package com.zakin.readerzmultiplatform.android.ui.composable.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.android.models.data.MangaViewModel
import com.zakin.readerzmultiplatform.models.Manga

@Composable
fun SiteMangaListItem(manga: Manga) {
    Row(Modifier.padding(20.dp, 15.dp)) {
        manga.name?.let { Text(text = it, fontSize = 14.sp, color = MaterialTheme.colors.secondary) }
    }
}