package com.motlagh.feature.player.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.motlagh.core.mvi.BaseViewModel
import com.motlagh.feature.player.data.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: dagger.Lazy<PlayerRepository>,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PlayerUiState, PlayerUiState.Partial, Nothing, PlayerIntent>(
    savedStateHandle = savedStateHandle,
    initialState = PlayerUiState.initialState(
        videoUrl = savedStateHandle.get<String>("videoUrl") ?: "https://cdn.pixabay.com/video/2016/02/14/2165-155327596_small.mp4"
    ),
    savableViewModel = true
) {

    init {
        val videoUrl = savedStateHandle.get<String>("videoUrl") ?: "https://cdn.pixabay.com/video/2016/02/14/2165-155327596_small.mp4"
        if (videoUrl.isNotEmpty()) {
            viewModelScope.launch {
                playerRepository.get().updateVideoUrl("https://cdn.pixabay.com/video/2023/10/12/184734-873923034_small.mp4")
                delay(10000)
                playerRepository.get().updateVideoUrl(videoUrl)

            }

        }
    }

    fun getPlayer(): ExoPlayer = playerRepository.get().getPlayer()

    override fun mapIntents(intent: PlayerIntent): Flow<PlayerUiState.Partial> = flow {
        when (intent) {
            is PlayerIntent.TogglePlayPause -> {
                if (uiState.value.isPlaying) {
                    getPlayer().pause()
                } else {
                    getPlayer().play()
                }
                emit(PlayerUiState.Partial.PlaybackStateChanged(!uiState.value.isPlaying))
            }
            is PlayerIntent.ToggleFullscreen -> {
                emit(PlayerUiState.Partial.FullscreenStateChanged(!uiState.value.isFullscreen))
            }
            is PlayerIntent.UpdatePosition -> {
                emit(PlayerUiState.Partial.PlaybackPositionChanged(intent.position))
            }
        }
    }

    override fun reduceUiState(
        previousState: PlayerUiState,
        partialState: PlayerUiState.Partial
    ): PlayerUiState {
        return when (partialState) {
            is PlayerUiState.Partial.PlaybackStateChanged -> {
                previousState.copy(isPlaying = partialState.isPlaying)
            }
            is PlayerUiState.Partial.FullscreenStateChanged -> {
                previousState.copy(isFullscreen = partialState.isFullscreen)
            }
            is PlayerUiState.Partial.PlaybackPositionChanged -> {
                previousState.copy(playbackPosition = partialState.position)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        playerRepository.get().release()
    }
}