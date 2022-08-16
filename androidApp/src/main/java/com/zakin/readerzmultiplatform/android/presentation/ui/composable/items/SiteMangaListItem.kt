package com.zakin.readerzmultiplatform.android.presentation.ui.composable.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.models.Manga

@Composable
fun SiteMangaListItem(manga: Manga, router: Router) {
    Row(Modifier.padding(15.dp).fillMaxWidth().clickable { router.openMangaView(manga) }) {
        Text(text = manga.name, fontSize = 14.sp, color = MaterialTheme.colors.secondary)
    }
}