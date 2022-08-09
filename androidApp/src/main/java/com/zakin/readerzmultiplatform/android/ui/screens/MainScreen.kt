package com.zakin.readerzmultiplatform.android.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zakin.readerzmultiplatform.android.routing.Router
import com.zakin.readerzmultiplatform.android.routing.RouterImpl
import com.zakin.readerzmultiplatform.android.ui.NavRoutes
import com.zakin.readerzmultiplatform.android.ui.services.ScanService

@Composable
fun MainScreen(navController: NavHostController) {

    val scanService: ScanService = viewModel()

    val router: Router = RouterImpl(navController, scanService)

    val startDestination = remember { mutableStateOf(NavRoutes.Home.route) }
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
            /*SplashPage(
                goBack = {
                    startDestination.value = NavRoutes.Home.route
                }
            )*/
        }
        composable(NavRoutes.Home.route) {
            TabWithPager(router)
        }

        composable(NavRoutes.Manga.route) {
            SiteView(router, scanService)
        }

        composable(NavRoutes.Settings.route) {
        }
    }
}