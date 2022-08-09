package com.zakin.readerzmultiplatform.android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zakin.readerzmultiplatform.android.models.Site
import com.zakin.readerzmultiplatform.android.models.data.MangaViewModel
import com.zakin.readerzmultiplatform.android.ui.composable.items.BibliothequeListItem
import com.zakin.readerzmultiplatform.android.ui.composable.toolbar.SiteToolbar
import com.zakin.readerzmultiplatform.android.ui.theme.ReaderzMultiplatformTheme

class SiteActivity : ComponentActivity() {

    private val site: Site by lazy {
        intent?.getSerializableExtra(SITE_ID) as Site
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderzMultiplatformTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text(text = "Test ${site.name}")
                }
            }
        }
    }

    companion object {
        private const val SITE_ID = "site_id"
        fun newIntent(context: Context, site: Site) =
            Intent(context, SiteActivity::class.java).apply {
                putExtra(SITE_ID, site)
            }
    }
}