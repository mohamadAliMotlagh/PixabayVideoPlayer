package com.motlagh.feature.player.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.media3.exoplayer.ExoPlayer
import com.motlagh.core.mvi.BaseViewModel
import com.motlagh.feature.player.domain.PlayerRepository
import com.motlagh.feature.player.domain.VideoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
internal class PlayerViewModel @Inject constructor(
    private val playerRepository: dagger.Lazy<PlayerRepository>,
    private val videoDetailUseCase: VideoDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PlayerUiState, PlayerUiState.Partial, Nothing, PlayerIntent>(
    savedStateHandle = savedStateHandle,
    initialState = PlayerUiState.initialState(),
    savableViewModel = true
) {


    private val videoDetailFlow: Flow<PlayerUiState.Partial> =
        savedStateHandle.getStateFlow("videoID", "")
            .flatMapLatest { videoID ->
                flow {
                    videoDetailUseCase(videoID)
                        .onSuccess {
                            emit(PlayerUiState.Partial.PlayerDetailReceived(it))
                        }.onFailure {
                            emit(PlayerUiState.Partial.PlayerDetailEmpty)
                        }
                }
            }

    init {
        acceptChanges(videoDetailFlow)
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

            PlayerUiState.Partial.PlayerDetailEmpty -> {
                playerRepository.get().updateVideoUrl("")
                previousState.copy(isEmpty = true)
            }

            is PlayerUiState.Partial.PlayerDetailReceived -> {
                playerRepository.get().updateVideoUrl(partialState.playerDetail.videoUrl)
                previousState.copy(
                    isEmpty = false,
                    uploaderName = partialState.playerDetail.username,
                    tags = partialState.playerDetail.tags,
                    views = partialState.playerDetail.views,
                    likes = partialState.playerDetail.likes,
                    comments = partialState.playerDetail.comments
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        playerRepository.get().release()
    }
}