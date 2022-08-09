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

@Composable
fun BibliothequeListItem(manga: MangaViewModel) {
    Row(Modifier.padding(20.dp, 15.dp)) {
        Text(text = manga.text, fontSize = 14.sp, color = MaterialTheme.colors.secondary)
    }
}