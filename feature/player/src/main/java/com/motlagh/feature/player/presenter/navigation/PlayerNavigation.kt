package com.motlagh.feature.player.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.motlagh.feature.player.presenter.ui.PlayerScreen

const val PLAYER_ROUTE = "player/{videoUrl}"

fun NavGraphBuilder.playerScreen(
    onBackPressed: () -> Unit
) {
    composable(
        route = PLAYER_ROUTE,
    ) { backStackEntry ->
        val videoUrl = backStackEntry.arguments?.getString("videoUrl") ?: ""
        PlayerScreen(
            onBackPressed = onBackPressed
        )
    }
}

fun NavHostController.navigateToPlayer(videoUrl: String) {
    navigate("player/$videoUrl")
} 