package com.zakin.readerzmultiplatform.android.core

sealed class NavRoutes(val route: String, val tag: String? = null) {
    object Home: NavRoutes("home")
    object Site: NavRoutes("site")
    object Manga: NavRoutes("manga")
    object Splash: NavRoutes("splash")

}
