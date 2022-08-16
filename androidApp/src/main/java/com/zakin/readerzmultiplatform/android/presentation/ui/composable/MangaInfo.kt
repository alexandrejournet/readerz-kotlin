package com.zakin.readerzmultiplatform.android.presentation.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.models.Manga

@Composable
fun MangaInfo(manga: Manga) {
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
                            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(5.dp))
                            .aspectRatio(5f / 7f)
                            .align(Alignment.CenterVertically)
                        ) {
                            Text(text="Image container", color = MaterialTheme.colors.secondary)
                        }
                    }
                    Column {
                        // TODO:  AJOUTER UN IF POUR CUT LA LONGUEUR
                        Text(text = manga.name, fontSize = 24.sp, color = MaterialTheme.colors.secondary)
                        Text(text = manga.author, fontSize = 14.sp, color = MaterialTheme.colors.secondary)
                        Text(text = manga.tag.toString(), fontSize = 14.sp, color = MaterialTheme.colors.secondary)
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