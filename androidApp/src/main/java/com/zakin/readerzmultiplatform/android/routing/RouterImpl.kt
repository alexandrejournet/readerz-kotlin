package com.zakin.readerzmultiplatform.android.routing

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.NavHostController
import com.zakin.readerzmultiplatform.android.models.Site
import com.zakin.readerzmultiplatform.android.ui.NavRoutes
import com.zakin.readerzmultiplatform.android.ui.services.ScanService

class RouterImpl(
    private val navHostController: NavHostController,
    private val scanService: ScanService,
    private val startDestination: String = "home",
): Router {

    private fun navigate(
        page : NavRoutes,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ){
        navHostController.apply {
            navigate(page.route){
                if(removeFromHistory){
                    if(singleTop){
                        popUpTo(NavRoutes.Home.route)
                    }else{
                        popUpTo(0){
                            saveState = false
                        }
                    }
                } else{
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }

    override fun goBack() {
        navHostController.apply {
            navigateUp()
            navigate(startDestination){
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    @SuppressLint("LongLogTag")
    override fun <T: Any> getArgs(tag: String): T? {
        return try{
            navHostController.previousBackStackEntry?.arguments?.get(tag) as T?
        } catch (ex: Exception){
            Log.d("Exception from getArgs: ", ex.toString())
            null
        }
    }

    override fun openHome() {
        navigate(NavRoutes.Home, removeFromHistory = true, singleTop = true)
    }

    override fun openMangaView(site: Site) {
        setSite(site);
        navHostController.navigate(NavRoutes.Manga.route)
    }

    override fun openReader() {
        TODO("Not yet implemented")
    }

    override fun <T: Any> putArgs(key: String, value: T) {
        navHostController.currentBackStackEntry?.savedStateHandle?.set(
            key= key,
            value = value
        )
    }

    private fun setSite(site: Site) {
        scanService.addSite(site)
    }
}