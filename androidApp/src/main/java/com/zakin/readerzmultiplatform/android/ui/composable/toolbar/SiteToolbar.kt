package com.zakin.readerzmultiplatform.android.ui.composable.toolbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zakin.readerzmultiplatform.android.ui.theme.ReaderzMultiplatformTheme

@Composable
fun SiteToolbar(siteName: String, onClickBack: () -> Unit) {
    ReaderzMultiplatformTheme() {
        TopAppBar(
            title = {
                Text(
                    text = siteName,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondary
                )
            },
            navigationIcon = {
                IconButton(onClick = { onClickBack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary,
                    )
                }
            }
        )
    }
}


