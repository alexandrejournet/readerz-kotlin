package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.createBitmap
import com.zakin.readerzmultiplatform.ScrapService
import com.zakin.readerzmultiplatform.models.Page
import java.util.concurrent.Executors

@Composable
fun PageScreen(page: Page) {

    var image: ImageBitmap? = createBitmap(1000, 1000).asImageBitmap()

    var loading = true
    var error = false

    val executor = Executors.newSingleThreadExecutor()
    executor.execute {
        val bytes = ScrapService().getPage(page.link)
        if (bytes.isNotEmpty()) {
            image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
        } else {
            error = true
        }
        loading = false
    }

    Crossfade(targetState = loading) { it ->

        when(it) {
            true -> {
                Text(text="Chargement de la page ${page.pageNumber}", color = MaterialTheme.colors.secondary)
            }
            false -> {
                if (!error) {
                    image?.let {
                        Image(
                            bitmap = it,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                } else {
                    Text(text="Erreur au chargement de la page ${page.pageNumber}", color = MaterialTheme.colors.secondary)
                }
            }
        }
    }


}