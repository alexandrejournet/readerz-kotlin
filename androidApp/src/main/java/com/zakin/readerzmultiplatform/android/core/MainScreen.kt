package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zakin.readerzmultiplatform.android.core.NavRoutes
import com.zakin.readerzmultiplatform.android.core.routing.Router
import com.zakin.readerzmultiplatform.android.core.routing.RouterImpl
import com.zakin.readerzmultiplatform.android.data.ScanService

@Composable
fun MainScreen(navController: NavHostController) {

    val scanService: ScanService = viewModel()

    val router: Router = RouterImpl(navController, scanService)

    val startDestination = remember { mutableStateOf(NavRoutes.Splash.route) }
    LaunchedEffect(startDestination){
        if(startDestination.value == NavRoutes.Home.route){
            router.openHome()
        }
    }


    NavHost(
        navController = navController,
        startDestination = startDestination.value,
    ) {
        composable(NavRoutes.Splash.route){
            SplashScreen(
                goBack = {
                    startDestination.value = NavRoutes.Home.route
                }
            )
        }
        composable(NavRoutes.Home.route) {
            TabWithPager(router)
        }

        composable(NavRoutes.Site.route) {
            SiteScreen(router, scanService)
        }

        composable(NavRoutes.Manga.route) {
            MangaScreen(router, scanService)
        }

        composable(NavRoutes.Reader.route) {
            ReaderScreen(router, scanService)
        }
    }
}