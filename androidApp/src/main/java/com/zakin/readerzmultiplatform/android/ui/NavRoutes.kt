package com.zakin.readerzmultiplatform.android.ui

sealed class NavRoutes(val route: String, val tag: String? = null) {
    object Home: NavRoutes("home")
    object Sites: NavRoutes("sites")
    object Settings: NavRoutes("settings")
    object Manga: NavRoutes("manga")
    object Splash: NavRoutes("splash")

}
