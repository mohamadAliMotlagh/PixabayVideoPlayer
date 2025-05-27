package com.motlagh.feature.search.presenter.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.motlagh.feature.search.presenter.ui.SearchVideoRoute
import kotlinx.serialization.Serializable


@Serializable
object SearchRoute

fun NavController.navigateToSearch(navOptions: NavOptions) =
    navigate(route = SearchRoute, navOptions)

fun NavGraphBuilder.searchScreen(
    onVideoItemClicked: (String) -> Unit,
    onBookmarkClicked: () -> Unit
) {
    composable<SearchRoute> {
        val onItemClicks = remember {
            { id: String ->
                onVideoItemClicked(id)
            }
        }
        SearchVideoRoute(onItemClicks = onItemClicks, onBookmarkClicked = onBookmarkClicked)
    }
}