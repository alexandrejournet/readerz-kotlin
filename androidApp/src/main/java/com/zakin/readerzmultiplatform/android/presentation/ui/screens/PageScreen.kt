package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.core.graphics.createBitmap
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.android.presentation.ui.composable.ZoomableImage
import com.zakin.readerzmultiplatform.models.Page
import java.util.concurrent.Executors

@Composable
fun PageScreen(page: Page) {

    var image by remember { mutableStateOf(createBitmap(1000, 1000).asImageBitmap()) }

    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }

    var alreadyScrapped by remember { mutableStateOf(false) }

    if (!alreadyScrapped) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val bytes = ScrapService().getPage(page.link)
            if (bytes.isNotEmpty()) {
                image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
            } else {
                error = true
            }
            loading = false
            alreadyScrapped = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            Text(
                text = "Chargement de la page ${page.pageNumber}",
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Center
            )
        } else {
            if (!error) {
                ZoomableImage(
                    bitmap = image
                )
            } else {
                Text(
                    text = "Erreur au chargement de la page ${page.pageNumber}",
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}