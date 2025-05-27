package com.motlagh.feature.bookmark.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.motlagh.core.domain.bookmarking.domain.RemoveBookmarkUseCase
import com.motlagh.core.mvi.BaseViewModel
import com.motlagh.feature.bookmark.domain.BookmarksUseCase
import com.motlagh.feature.bookmark.presenter.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookmarksUseCase: BookmarksUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase
) : BaseViewModel<BookmarkUIState, BookmarkUIState.Partial, Nothing, BookmarkIntent>(
    savedStateHandle = savedStateHandle,
    initialState = BookmarkUIState.initialState()
) {

    init {
        viewModelScope.launch {
            acceptChanges(bookmarksUseCase().map {
                BookmarkUIState.Partial.NewListReceived(it.map { it.toUIModel() })
            })
        }
    }

    override fun mapIntents(intent: BookmarkIntent): Flow<BookmarkUIState.Partial> = flow {
        when (intent) {
            is BookmarkIntent.OnItemClick -> {

            }

            is BookmarkIntent.OnBookMarkClicked -> {
                removeBookmarkUseCase(intent.videoID)
            }
        }
    }

    override fun reduceUiState(
        previousState: BookmarkUIState,
        partialState: BookmarkUIState.Partial
    ): BookmarkUIState {
        return when (partialState) {
            is BookmarkUIState.Partial.NewListReceived -> {
                previousState.copy(list = partialState.videos)
            }
        }
    }
}