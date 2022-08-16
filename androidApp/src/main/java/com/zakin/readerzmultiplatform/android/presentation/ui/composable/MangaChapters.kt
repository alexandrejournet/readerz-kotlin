package com.zakin.readerzmultiplatform.android.presentation.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.items.ChapterItem
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.Manga

@Composable
fun MangaChapters(manga: Manga, router: Router) {

    ReaderzMultiplatformTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column {
                Text(text = "${manga.chapters.size} chapitres", fontSize = 14.sp, fontWeight = FontWeight.Bold , color = MaterialTheme.colors.secondary, modifier=Modifier.padding(15.dp, 10.dp))
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = manga.chapters,
                        itemContent = {
                            ChapterItem(chapter = it, router = router)
                        })
                }
            }
        }
    }
}