package com.zakin.readerzmultiplatform.android.presentation.ui.composable

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material.icons.rounded.DownloadForOffline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.placeholderImg
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.placeholderImgIcon
import com.zakin.readerzmultiplatform.models.Manga
import java.util.concurrent.Executors

@Composable
fun MangaInfo(manga: Manga) {

    var image by remember { mutableStateOf(createBitmap(1000, 1000).asImageBitmap()) }

    var loading by remember {mutableStateOf(true) }

    val executor = Executors.newSingleThreadExecutor()
    executor.execute {
        val bytes = ScrapService().getPage(manga.coverLink)
        if (bytes.isNotEmpty()) {
            image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
        }
        loading = false
    }

    ReaderzMultiplatformTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier= Modifier
                        .width(150.dp)
                        .padding(15.dp)
                    ) {
                        Box(modifier= Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .border(BorderStroke(1.dp, placeholderImgIcon), shape = RoundedCornerShape(5.dp))
                            .aspectRatio(5f / 7f)
                            .background(placeholderImg),
                            contentAlignment = Alignment.Center,

                        ) {
                            if(loading) {
                                Icon(
                                    imageVector = Icons.Rounded.BrokenImage,
                                    contentDescription = null,
                                    tint = placeholderImgIcon,
                                )
                            } else {
                                image.let {
                                    Image(
                                        bitmap = it,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                    Column {
                        // TODO:  AJOUTER UN IF POUR CUT LA LONGUEUR
                        Text(text = manga.name, fontSize = 24.sp, color = MaterialTheme.colors.secondary)
                        Text(text = manga.author, fontSize = 14.sp, color = MaterialTheme.colors.secondary)
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colors.primaryVariant)
                            .padding(4.dp, 2.dp),
                            contentAlignment = Alignment.Center) {
                            Text(text = manga.tag.toString(), fontSize = 12.sp, color = MaterialTheme.colors.primary)
                        }

                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(15.dp, 10.dp)) {
                    // TODO: Bloquer la hauteur avec un bouton
                    Text(text = "Résumé", fontSize = 14.sp, color = MaterialTheme.colors.secondary)
                }
            }
        }
    }
}