package com.motlagh.feature.search.presenter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.motlagh.core.ui.videolist.VideoList
import com.motlagh.feature.search.presenter.SearchIntent
import com.motlagh.feature.search.presenter.SearchUiState
import com.motlagh.feature.search.presenter.SearchViewModel


@Composable
internal fun SearchVideoRoute(
    modifier: Modifier = Modifier.fillMaxSize(),
    searchViewModel: SearchViewModel = hiltViewModel(),
    onItemClicks: (videoID: String) -> Unit
) {
    val uiState = searchViewModel.uiState.collectAsStateWithLifecycle().value

    SearchVideoScreen({ uiState })
    {
        when (it) {
            is SearchIntent.OnItemClicks -> onItemClicks(it.videoID)
            else -> {
                searchViewModel.acceptIntent(it)
            }
        }
    }
}

@Composable
private fun SearchVideoScreen(
    uiState: () -> SearchUiState,
    onIntent: (SearchIntent) -> Unit
) {
    SearchableContainer(
        searchQuery = { uiState().query },

        onQueryChange = {
            onIntent(SearchIntent.OnQueryChanged(it))
        },
        content = {
            VideoList(
                modifier = Modifier.fillMaxSize(),
                videos = uiState().videos,
                onVideoClick = { onIntent(SearchIntent.OnItemClicks(it))},
                onBookmarkClick = {id, hasBookmark ->
                    onIntent(SearchIntent.BookMarkClicked(id, hasBookmark))
                }
            )
        }
    )


//        TextField(value = uiState().query, onValueChange = {
//            onIntent(SearchIntent.OnQueryChanged(it))
//        })

}