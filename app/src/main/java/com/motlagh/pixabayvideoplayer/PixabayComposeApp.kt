package com.motlagh.pixabayvideoplayer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.motlagh.core.designsystem.theme.PixabayVideoPlayerTheme
import com.motlagh.feature.player.presenter.navigation.navigateToPlayer
import com.motlagh.feature.player.presenter.navigation.playerScreen
import com.motlagh.feature.search.presenter.navigation.SearchRoute
import com.motlagh.feature.search.presenter.navigation.searchScreen


@SuppressLint("ComposeModifierMissing")
@Composable
fun PixabayComposeApp() {
    PixabayVideoPlayerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                AppNavigation()
            }
        }
    }
}


@Composable
private fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SearchRoute) {
        searchScreen(onVideoItemClicked = {
            navController.navigateToPlayer(it)
        })

        playerScreen(onBackPressed = {
            navController.popBackStack()
        })
    }
}