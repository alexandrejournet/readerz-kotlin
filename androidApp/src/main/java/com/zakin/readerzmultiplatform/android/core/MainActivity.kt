package com.zakin.readerzmultiplatform.android.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.zakin.readerzmultiplatform.Greeting
import com.zakin.readerzmultiplatform.android.presentation.ui.screens.MainScreen
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.ReaderzMultiplatformTheme

fun greet(): String {
    return Greeting().greeting()
}

val tabsArray = arrayOf(
    "Bibliothèque",
    "Explorer",
    "Paramètres"
)

class MainActivity : ComponentActivity() {
    //lateinit var binding: ActivityMainBinding

    //var isSearchBarShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderzMultiplatformTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    MainScreen(navController = navController)
                }
            }
        }

        /*binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.pagerContainer
        val tabLayout = binding.tabLayout

        val adapter = TabPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
*/


        /*searchEdit.visibility = View.INVISIBLE
        backBtn.visibility = View.INVISIBLE

        searchBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setSearchState(true)
                searchEdit.visibility = View.VISIBLE
                backBtn.visibility = View.VISIBLE
                searchBtn.visibility = View.INVISIBLE
                toolbarTitle.visibility = View.INVISIBLE
            }
        })

        backBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setSearchState(false)
                searchEdit.visibility = View.INVISIBLE
                backBtn.visibility = View.INVISIBLE
                searchBtn.visibility = View.VISIBLE
                toolbarTitle.visibility = View.VISIBLE
            }
        })*/

        /*val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()*/
    }

    /*private fun setToolbarButton(position: Int) {

    }

    private fun setTextWithPosition(position: Int, toolbarTitle: TextView) {
        toolbarTitle.text = tabsArray[position]
    }

    private fun setSearchState(state: Boolean) {
        isSearchBarShown = state
    }*/
}

@Preview
@Composable
fun PreviewIconTab() {
    val context = LocalContext.current
    //TabWithPager()
}





