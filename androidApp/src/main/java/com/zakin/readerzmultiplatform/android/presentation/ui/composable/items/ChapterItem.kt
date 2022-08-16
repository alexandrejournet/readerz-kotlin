package com.zakin.readerzmultiplatform.android.presentation.ui.composable.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadForOffline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.Chapter

@Composable
fun ChapterItem(chapter: Chapter, router: Router) {
    ReaderzMultiplatformTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { // Todo: ouvrir le reader
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = chapter.name, fontSize = 14.sp, color = MaterialTheme.colors.secondary)
                Text(text = chapter.addedDate, fontSize = 11.sp, color = MaterialTheme.colors.secondary)
            }
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Rounded.DownloadForOffline,
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary,
                )
            }
        }
    }
}