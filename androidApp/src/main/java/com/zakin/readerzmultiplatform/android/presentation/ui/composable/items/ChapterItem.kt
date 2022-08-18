package com.zakin.readerzmultiplatform.android.presentation.ui.composable.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadForOffline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.Chapter
import com.zakin.readerzmultiplatform.models.enums.Badges

@Composable
fun ChapterItem(chapter: Chapter, router: Router) {
    ReaderzMultiplatformTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { router.openReader(chapter) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = chapter.name,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.secondary,
                        letterSpacing = 0.5.sp
                    )
                    if (chapter.subName.isNotBlank()) {
                        val subname = if (chapter.subName.length > 30) chapter.subName.substring(0, 30) + "..." else chapter.subName
                        Text(text = subname, fontSize = 12.sp, color = MaterialTheme.colors.secondary, fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic)
                    }
                    if (chapter.badge != Badges.NONE) {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colors.primaryVariant)
                            .padding(4.dp, 2.dp),
                            contentAlignment = Alignment.Center) {
                            Text(text = chapter.badge.name, fontSize = 12.sp, color = MaterialTheme.colors.primary)
                        }
                    }
                }
                Text(text = chapter.addedDate, fontSize = 12.sp, color = MaterialTheme.colors.secondary, fontWeight = FontWeight.Light)
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