package com.motlagh.pixabayvideoplayer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.motlagh.core.designsystem.theme.PixabayVideoPlayerTheme
import com.motlagh.feature.bookmark.presenter.navigation.bookmarkScreen
import com.motlagh.feature.bookmark.presenter.navigation.navigateToBookmark
import com.motlagh.feature.player.presenter.navigation.navigateToPlayer
import com.motlagh.feature.player.presenter.navigation.playerScreen
import com.motlagh.feature.search.presenter.navigation.SearchRoute
import com.motlagh.feature.search.presenter.navigation.searchScreen

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("ComposeModifierMissing")
@Composable
fun PixabayComposeApp() {
    PixabayVideoPlayerTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) { innerPadding ->
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
        searchScreen(
            onVideoItemClicked = navController::navigateToPlayer,
            onBookmarkClicked = navController::navigateToBookmark
        )

        playerScreen(onBackPressed = navController::popBackStack)

        bookmarkScreen(
            onBackPressed = navController::popBackStack,
            onVideoClicked = navController::navigateToPlayer
        )
    }
}