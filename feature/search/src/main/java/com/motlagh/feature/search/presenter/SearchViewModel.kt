package com.motlagh.feature.search.presenter

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.motlagh.core.mvi.BaseViewModel
import com.motlagh.feature.search.domain.SearchUseCase
import com.motlagh.feature.search.presenter.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val queryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            queryFlow
                .debounce(500)
                .filter { it.isNotBlank() }
                .map { query -> SearchUiState.Partial.NewQueryReceived(query) }
                .collectLatest { partialState ->
                    acceptChanges(flowOf(partialState))
                    searchVideo(partialState.query)
                }
        }
    }

   private val videosList = mutableStateListOf<Video>()

    fun searchVideo(query: String) {
        viewModelScope.launch {
            searchUseCase(query)
                .map { result ->
                    result.getOrNull()?.let { videoItems ->
                        val mappedVideos = videoItems.map { it.toUIModel() }

                        videosList.clear()
                        videosList.addAll(mappedVideos)

                        SearchUiState.Partial.NewListReceived(mappedVideos)
                    } ?: SearchUiState.Partial.NewListReceived(emptyList())
                }
                .flowOn(Dispatchers.IO)
                .let { acceptChanges(it) }
        }
    }



    override fun mapIntents(intent: SearchIntent): Flow<SearchUiState.Partial> = flow {
        when (intent) {
            is SearchIntent.OnQueryChanged -> {
                queryFlow.update { intent.query }
                emit(SearchUiState.Partial.NewQueryReceived(intent.query))
            }
        }
    }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.Partial
    ): SearchUiState {
        return when (partialState) {
            is SearchUiState.Partial.NewListReceived -> {
                return previousState.copy(videos = partialState.videos)
            }

            is SearchUiState.Partial.NewQueryReceived -> {
                return previousState.copy(query = partialState.query)
            }
        }
    }
}