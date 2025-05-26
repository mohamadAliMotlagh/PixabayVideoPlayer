package com.motlagh.feature.search.presenter

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.motlagh.core.mvi.BaseViewModel
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

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchUseCase: SearchUseCase
) : BaseViewModel<SearchUiState, SearchUiState.Partial, Nothing, SearchIntent>(
    savableViewModel = true,
    savedStateHandle = savedStateHandle,
    initialState = SearchUiState.initialState()
) {
    /**
     * i used this because list is not stable and
     * cause to extra recomposition in list when updating the list.
     * */
    private val videosList: SnapshotStateList<Video> = uiState.value.videos.toMutableStateList()

    /**
     * i used this for apply debounce function in viewmodel.
     * i could do that in ui but i prefer to do it in viewmodel.
     * */
    private val queryFlow = MutableStateFlow(uiState.value.query)
    private var searchResult = queryFlow
        .debounce(500)
        .filter { it.isNotBlank() }
        .flatMapLatest { it ->
            searchUseCase(it)
        }.map { result ->
            result.getOrNull()?.let { videoItems ->
                val mappedVideos = videoItems.map { it.toUIModel() }
                videosList.clear()
                videosList.addAll(mappedVideos)
                SearchUiState.Partial.NewListReceived(mappedVideos)
            } ?: SearchUiState.Partial.NewListReceived(emptyList())
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            SearchUiState.Partial.NewListReceived(emptyList())
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
        }
    }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.Partial
    ): SearchUiState {
        return when (partialState) {
            is SearchUiState.Partial.NewListReceived -> {
                previousState.copy(videos = partialState.videos)
            }

            is SearchUiState.Partial.NewQueryReceived -> {
                previousState.copy(query = partialState.query)
            }
        }
    }
}