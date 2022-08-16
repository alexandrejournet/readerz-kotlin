package com.zakin.readerzmultiplatform.android.core.routing

import com.zakin.readerzmultiplatform.android.domain.models.Site
import com.zakin.readerzmultiplatform.models.Manga

interface Router {
    // TABS
    fun openHome()

    // Sub routing
    fun openMangaView(manga: Manga)
    fun openSiteView(site: Site)
    fun openReader()

    fun goBack()
    fun <T: Any> getArgs(tag: String): T?
    fun <T: Any> putArgs(key: String, value: T)
}