package com.zakin.readerzmultiplatform.android.ui.composable.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zakin.readerzmultiplatform.android.models.Site
import com.zakin.readerzmultiplatform.android.models.data.MangaViewModel
import com.zakin.readerzmultiplatform.android.routing.Router
import com.zakin.readerzmultiplatform.android.ui.NavRoutes
import com.zakin.readerzmultiplatform.android.ui.services.ScanService

@Composable
fun ExplorerListItem(site: Site, router: Router) {
    Row(
        modifier = Modifier
            .padding(20.dp, 15.dp)
            .clickable { router.openMangaView(site) }
    ) {
        Text(text = site.name, fontSize = 14.sp, color = MaterialTheme.colors.secondary)
    }
}
