package com.motlagh.feature.search.presenter

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.motlagh.core.domain.bookmarking.domain.AddBookmarkUseCase
import com.motlagh.core.domain.bookmarking.domain.RemoveBookmarkUseCase
import com.motlagh.core.mvi.BaseViewModel
import com.motlagh.core.ui.videoItem.VideoItemUiModel
import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.domain.SearchUseCase
import com.motlagh.feature.search.presenter.SearchUiState.Partial.HasError
import com.motlagh.feature.search.presenter.SearchUiState.Partial.Loading
import com.motlagh.feature.search.presenter.SearchUiState.Partial.NewListReceived
import com.motlagh.feature.search.presenter.SearchUiState.Partial.NewQueryReceived
import com.motlagh.feature.search.presenter.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@Stable
@HiltViewModel
internal class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchUseCase: SearchUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : BaseViewModel<SearchUiState, SearchUiState.Partial, Nothing, SearchIntent>(
    savedStateHandle = savedStateHandle,
    initialState = SearchUiState.initialState()
) {
    /**
     * i used this because list is not stable and
     * cause to extra recomposition in list when updating the list.
     * */
    private val videosList = mutableStateListOf<VideoItemUiModel>()

    /**
     * i used this for apply debounce function in viewmodel.
     * i could do that in ui but i prefer to do it in viewmodel.
     * */
    private val queryFlow = MutableStateFlow(uiState.value.query)
    private val searchResult = queryFlow
        .debounce(500)
        .filter { it.isNotBlank() }
        .onEach {
            acceptIntent(SearchIntent.Loading(true))
        }
        .flatMapLatest{
            performSearch(it)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            NewListReceived(emptyList())
        )

    init {
        acceptChanges(searchResult)
    }

    private suspend fun performSearch(query: String): Flow<SearchUiState.Partial> {
        return searchUseCase(query).flatMapLatest { result ->
            mapResult(result)
        }
    }


    private fun mapResult(result: Result<List<VideoItemDomainModel>>) = flow {
        result.fold(onSuccess = { videoItems ->
            val mappedVideos = videoItems.map { it.toUIModel() }
            videosList.clear()
            videosList.addAll(mappedVideos)
            emit(Loading(mappedVideos.isEmpty()))
            emit(NewListReceived(videosList))
        }, onFailure = {
            emit(NewListReceived(emptyList()))
            emit(HasError(true))
        })
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
                } else {
                    addBookmarkUseCase(intent.videoID)
                }
            }

            SearchIntent.OnBookmarkButtonClicked -> {}
            is SearchIntent.Loading -> emit(Loading(intent.isLoading))

            is SearchIntent.Error -> {}
        }
    }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.Partial
    ): SearchUiState {
        return when (partialState) {
            is NewListReceived -> {
                previousState.copy(
                    videos = partialState.videos,
                    showError = false
                )
            }

            is NewQueryReceived -> {
                previousState.copy(
                    query = partialState.query
                )
            }

            is Loading -> {
                previousState.copy(loading = partialState.isLoading, showError = false)
            }

            is HasError -> {
                previousState.copy(loading = false, showError = partialState.show)
            }
        }
    }
}