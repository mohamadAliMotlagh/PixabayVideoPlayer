package com.motlagh.feature.player.data

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.motlagh.feature.player.domain.PlayerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PlayerRepository {

    private var player: ExoPlayer? = null
    private var currentUrl: String? = null
    private var onPositionChanged: ((Long) -> Unit)? = null
    private var isInitialized = false
    private var savedPosition: Long = 0L
    private var shouldPlay: Boolean = false

    override fun getPlayer(): ExoPlayer {
        if (player == null) {
            player = ExoPlayer.Builder(context).build().also { exoPlayer ->
                if (currentUrl != null) {
                    val mediaItem = MediaItem.fromUri(currentUrl!!)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.prepare()
                }

                exoPlayer.addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        if (playbackState == Player.STATE_READY) {

                            if (!isInitialized) {

                                isInitialized = true
                                exoPlayer.seekTo(savedPosition)

                                if (shouldPlay) {
                                    exoPlayer.play()
                                }
                            }

                            onPositionChanged?.invoke(exoPlayer.currentPosition)
                        }
                    }
                })
            }
        }

        return player!!
    }

    override fun updateVideoUrl(url: String) {
        if (url != currentUrl) {
            currentUrl = url
            player?.let { exoPlayer ->
                val mediaItem = MediaItem.fromUri(url)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                isInitialized = false
            } ?: run {
                // If player is null, it will be initialized with this URL when getPlayer() is called
                isInitialized = false
            }
        }
    }

    override fun setOnPositionChangedListener(listener: (Long) -> Unit) {
        onPositionChanged = listener
        player?.let { exoPlayer ->
            if (exoPlayer.playbackState == Player.STATE_READY) {
                listener(exoPlayer.currentPosition)
            }
        }
    }

    override fun setPlaybackState(position: Long, shouldPlay: Boolean) {
        this.savedPosition = position
        this.shouldPlay = shouldPlay
        player?.let { exoPlayer ->
            if (exoPlayer.playbackState == Player.STATE_READY) {
                exoPlayer.seekTo(position)

                if (shouldPlay) {
                    exoPlayer.play()
                } else {
                    exoPlayer.pause()
                }
            }
        }
    }

    override fun release() {
        player?.release()
        player = null
        currentUrl = null
        isInitialized = false
        savedPosition = 0L
        shouldPlay = false
    }
}