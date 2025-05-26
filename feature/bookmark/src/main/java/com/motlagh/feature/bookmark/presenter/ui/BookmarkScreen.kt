package com.motlagh.feature.bookmark.presenter.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.motlagh.feature.bookmark.presenter.BookmarkIntent
import com.motlagh.feature.bookmark.presenter.BookmarkUIState
import com.motlagh.feature.bookmark.presenter.BookmarkViewModel

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    BookMarkList(uiState, viewModel::acceptIntent)
}

@Composable
fun BookMarkList(uiState: BookmarkUIState, onIntent: (BookmarkIntent) -> Unit) {

}