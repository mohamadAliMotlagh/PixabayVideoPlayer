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
object BookMarkRoute

fun NavHostController.navigateToBookmark() {
    navigate(BookMarkRoute)
}

fun NavGraphBuilder.bookmarkScreen(
    onBackPressed: () -> Unit
) {
    composable(
        route = BookMarkRoute::class,
    ) { backStackEntry ->
        BookmarkScreen()
    }
}




