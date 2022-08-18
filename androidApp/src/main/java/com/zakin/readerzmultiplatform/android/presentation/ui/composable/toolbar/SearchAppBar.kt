package com.zakin.readerzmultiplatform.android.presentation.ui.composable.toolbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.textColorToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    siteName: String,
    onClickBack: () -> Unit,
    searchText: String = "",
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
) {

    var showSearchBar by remember { mutableStateOf(false) }
    var showClearButton by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    if (showSearchBar) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { showSearchBar = false; onClearClick() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Bouton pour cacher l'input",
                        tint = textColorToolbar,
                    )
                }
            },
            actions = {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .onFocusChanged { focusState ->
                            showClearButton = (focusState.isFocused)
                        }
                        .focusRequester(focusRequester),
                    value = searchText,
                    onValueChange = onSearchTextChanged,
                    placeholder = {
                        Text(text = placeholderText,
                            color = textColorToolbar)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                    ),
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = showClearButton,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(onClick = { onClearClick() }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Bouton pour vider l'input",
                                    tint = textColorToolbar,
                                )
                            }
                        }
                    },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                )
            }
        )
    } else {
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
                        contentDescription = "Retour arrière",
                        tint = textColorToolbar,
                    )
                }
            },
            actions = {
                IconButton(onClick = { showSearchBar = true }) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Loupe pour ouvrir l'input",
                        tint = textColorToolbar,
                    )
                }
            }
        )
    }
}