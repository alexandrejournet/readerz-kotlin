package com.zakin.readerzmultiplatform.android.routing

import com.zakin.readerzmultiplatform.android.models.Site

interface Router {
    // TABS
    fun openHome()

    // Sub routing
    fun openMangaView(site: Site)
    fun openReader()

    fun goBack()
    fun <T: Any> getArgs(tag: String): T?
    fun <T: Any> putArgs(key: String, value: T)
}