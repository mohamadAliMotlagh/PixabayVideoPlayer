package com.motlagh.feature.player.presenter

import android.os.Parcelable
import com.motlagh.domain.video.VideoItemDomainModel
import kotlinx.parcelize.Parcelize


@Parcelize
data class PlayerUiState(
    val videoUrl: String,
    val isPlaying: Boolean = false,
    val isFullscreen: Boolean = false,
    val uploaderName: String = "John Doe",
    val tags: List<String> = listOf("nature", "beautiful", "landscape", "mountains", "sunset"),
    val views: Int = 1234567,
    val likes: Int = 45678,
    val comments: Int = 1234,
    val isEmpty: Boolean = true
) : Parcelable {
    sealed interface Partial {
        data class PlaybackStateChanged(val isPlaying: Boolean) : Partial
        data class FullscreenStateChanged(val isFullscreen: Boolean) : Partial
        data class PlayerDetailReceived(val playerDetail: VideoItemDomainModel) : Partial
        data object PlayerDetailEmpty : Partial
    }

    companion object {
        fun initialState() = PlayerUiState(
            videoUrl = "",

            )
    }
}


