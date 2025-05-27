package com.motlagh.feature.bookmark.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.motlagh.feature.bookmark.presenter.ui.BookmarkScreen
import kotlinx.serialization.Serializable

@Serializable
object BookMarksRoute

fun NavHostController.navigateToBookmark() {
    navigate(BookMarksRoute)
}

fun NavGraphBuilder.bookmarkScreen(
    onBackPressed: () -> Unit,
    onVideoClicked: (String) -> Unit,
) {
    composable(
        route = BookMarksRoute::class,
    ) { backStackEntry ->
        BookmarkScreen(onBackPressed, onVideoClicked)
    }
}




