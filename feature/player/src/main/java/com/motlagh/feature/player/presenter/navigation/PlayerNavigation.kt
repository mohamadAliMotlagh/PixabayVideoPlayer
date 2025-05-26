package com.motlagh.feature.player.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.motlagh.feature.player.presenter.ui.PlayerScreen
import kotlinx.serialization.Serializable

@Serializable
data class VideoPlayerRoute(val videoID: String)

fun NavHostController.navigateToPlayer(videoID: String) {
    navigate(VideoPlayerRoute(videoID))
}

fun NavGraphBuilder.playerScreen(
    onBackPressed: () -> Unit
) {
    composable(
        route = VideoPlayerRoute::class,
    ) { backStackEntry ->
        PlayerScreen(
            onBackPressed = onBackPressed
        )
    }
}




