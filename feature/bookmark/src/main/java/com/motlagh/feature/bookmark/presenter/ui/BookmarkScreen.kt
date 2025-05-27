package com.motlagh.feature.bookmark.presenter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.motlagh.core.ui.videolist.VideoList
import com.motlagh.feature.bookmark.presenter.BookmarkIntent
import com.motlagh.feature.bookmark.presenter.BookmarkUIState
import com.motlagh.feature.bookmark.presenter.BookmarkViewModel

@Composable
internal fun BookmarkScreen(
    onBackPressed: () -> Unit,
    onVideoClicked: (String) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    BookMarkList(uiState = uiState, onIntent = {
        when (it) {

            is BookmarkIntent.OnItemClick -> {
                onVideoClicked(it.videoID)
            }

            else -> {
                viewModel.acceptIntent(it)
            }
        }
    })
}


@Composable
fun BookMarkList(
    uiState: BookmarkUIState,
    onIntent: (BookmarkIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    VideoList(
        modifier = Modifier.fillMaxSize(),
        videos = { uiState.list },
        onVideoClick = {
            onIntent(BookmarkIntent.OnItemClick(it))
        }, onBookmarkClick = { id, hasBookmark ->
            onIntent(BookmarkIntent.OnBookMarkClicked(id))
        })
}