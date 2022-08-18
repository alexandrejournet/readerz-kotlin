package com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.textColorToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SiteToolbar(
    siteName: String,
    onClickBack: () -> Unit,
    enableSearch: Boolean = false,
    searchText: String? = "",
    placeholderText: String? = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    color: Color = MaterialTheme.colors.primaryVariant
) {

    ReaderzMultiplatformTheme {
        if (enableSearch) {
            SearchAppBar(
                siteName = siteName,
                onClickBack = { onClickBack() },
                searchText = searchText!!,
                placeholderText = placeholderText!!,
                onSearchTextChanged = onSearchTextChanged,
                onClearClick = onClearClick
            )
        } else {
            TopAppBar(
                backgroundColor = color,
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
}


