package com.motlagh.feature.bookmark.presenter

import androidx.lifecycle.SavedStateHandle
import com.motlagh.core.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<BookmarkUIState, BookmarkUIState.Partial, Nothing, BookmarkIntent>(
    savedStateHandle = savedStateHandle,
    initialState = BookmarkUIState.initialState()
) {
    override fun mapIntents(intent: BookmarkIntent): Flow<BookmarkUIState.Partial> = flow {
        when (intent) {
            is BookmarkIntent.OnItemClick -> {}
        }
    }

    override fun reduceUiState(
        previousState: BookmarkUIState,
        partialState: BookmarkUIState.Partial
    ): BookmarkUIState {
        return previousState
    }
}