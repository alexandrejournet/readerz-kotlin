package com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.textColorLight

@Composable
fun GenericToolbar(
    title: String,
    navigationIcon: @Composable() (() -> Unit)? = null) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primaryVariant) {
        Row(Modifier.padding(15.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = textColorLight
            )
        }
    }
}