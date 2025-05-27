package com.motlagh.feature.search.presenter

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.motlagh.core.domain.bookmarking.domain.AddBookmarkUseCase
import com.motlagh.core.domain.bookmarking.domain.RemoveBookmarkUseCase
import com.motlagh.core.mvi.BaseViewModel
import com.motlagh.core.ui.videoItem.VideoItemUiModel
import com.motlagh.feature.search.domain.SearchUseCase
import com.motlagh.feature.search.presenter.SearchUiState.Partial.*
import com.motlagh.feature.search.presenter.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@Stable
@HiltViewModel
internal class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchUseCase: SearchUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : BaseViewModel<SearchUiState, SearchUiState.Partial, Nothing, SearchIntent>(
    savableViewModel = true,
    savedStateHandle = savedStateHandle,
    initialState = SearchUiState.initialState()
) {
    /**
     * i used this because list is not stable and
     * cause to extra recomposition in list when updating the list.
     * */
    private val videosList: SnapshotStateList<VideoItemUiModel> = mutableStateListOf()

    /**
     * i used this for apply debounce function in viewmodel.
     * i could do that in ui but i prefer to do it in viewmodel.
     * */
    private val queryFlow = MutableStateFlow(uiState.value.query)
    private val searchResult = queryFlow
        .debounce(500)
        .filter { it.isNotBlank() }
        .flatMapLatest { it ->
            searchUseCase(it)
        }.map { result ->
            result.getOrNull()?.let { videoItems ->
                val mappedVideos = videoItems.map { it.toUIModel() }
                videosList.clear()
                videosList.addAll(mappedVideos)
                NewListReceived(mappedVideos)
            } ?: NewListReceived(emptyList())
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            NewListReceived(emptyList())
        )

    init {
        acceptChanges(searchResult)
    }

    override fun mapIntents(intent: SearchIntent): Flow<SearchUiState.Partial> = flow {
        when (intent) {
            is SearchIntent.OnQueryChanged -> {
                queryFlow.update { intent.query }
                emit(NewQueryReceived(intent.query))
            }

            is SearchIntent.OnItemClicks -> {}
            is SearchIntent.BookMarkClicked -> {
                if (intent.hasBookmark) {
                    removeBookmarkUseCase(intent.videoID)
                } else
                    addBookmarkUseCase(intent.videoID)
            }
        }
    }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.Partial
    ): SearchUiState {
        return when (partialState) {
            is NewListReceived -> {
                previousState.copy(videos = partialState.videos)
            }

            is NewQueryReceived -> {
                previousState.copy(query = partialState.query)
            }
        }
    }
}