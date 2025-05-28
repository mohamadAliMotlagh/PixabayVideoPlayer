package com.motlagh.feature.bookmark.presenter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.motlagh.core.ui.videolist.VideoList
import com.motlagh.feature.bookmark.R
import com.motlagh.feature.bookmark.presenter.BookmarkIntent
import com.motlagh.feature.bookmark.presenter.BookmarkUIState
import com.motlagh.feature.bookmark.presenter.BookmarkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BookmarkScreen(
    onBackPressed: () -> Unit,
    onVideoClicked: (String) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.bookmarks),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        BookMarkList(
            uiState = uiState,
            onIntent = {
                when (it) {
                    is BookmarkIntent.OnItemClick -> {
                        onVideoClicked(it.videoID)
                    }

                    else -> {
                        viewModel.acceptIntent(it)
                    }
                }
            }
        )
    }
}

@Composable
fun BookMarkList(
    uiState: BookmarkUIState,
    onIntent: (BookmarkIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    VideoList(
        modifier = modifier.fillMaxSize(),
        videos = uiState.list,
        onVideoClick = {
            onIntent(BookmarkIntent.OnItemClick(it))
        },
        onBookmarkClick = { id, hasBookmark ->
            onIntent(BookmarkIntent.OnBookMarkClicked(id))
        }
    )
}