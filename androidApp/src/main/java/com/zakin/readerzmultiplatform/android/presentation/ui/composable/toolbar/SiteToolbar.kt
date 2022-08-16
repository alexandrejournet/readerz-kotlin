package com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.textColorToolbar

@Composable
fun SiteToolbar(siteName: String, onClickBack: () -> Unit) {
    ReaderzMultiplatformTheme {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            title = {
                Text(
                    text = siteName,
                    style = MaterialTheme.typography.h6,
                    color = textColorToolbar
                )
            },
            navigationIcon = {
                IconButton(onClick = { onClickBack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = textColorToolbar,
                    )
                }
            }
        )
    }
}


