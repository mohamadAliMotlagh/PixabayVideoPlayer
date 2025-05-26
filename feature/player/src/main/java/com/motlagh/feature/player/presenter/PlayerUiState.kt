package com.motlagh.feature.player.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerUiState(
    val videoUrl: String,
    val isPlaying: Boolean = false,
    val isFullscreen: Boolean = false,
    val playbackPosition: Long = 0L,
    val uploaderName: String = "John Doe",
    val tags: List<String> = listOf("nature", "beautiful", "landscape", "mountains", "sunset"),
    val views: Int = 1234567,
    val likes: Int = 45678,
    val comments: Int = 1234
) : Parcelable {
    sealed interface Partial {
        data class PlaybackStateChanged(val isPlaying: Boolean) : Partial
        data class FullscreenStateChanged(val isFullscreen: Boolean) : Partial
        data class PlaybackPositionChanged(val position: Long) : Partial
    }

    companion object {
        fun initialState(videoUrl: String) = PlayerUiState(
            videoUrl = videoUrl
        )
    }
}


