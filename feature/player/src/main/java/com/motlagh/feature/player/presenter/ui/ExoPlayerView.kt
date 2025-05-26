package com.motlagh.feature.player.presenter.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class ExoPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val playerView: PlayerView
    private var onPositionChanged: ((Long) -> Unit)? = null
    private var onFullscreenChanged: ((Boolean) -> Unit)? = null

    init {
        playerView = PlayerView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            useController = true
            setFullscreenButtonClickListener { isFullscreen ->
                onFullscreenChanged?.invoke(isFullscreen)
            }
        }
        addView(playerView)
    }

    fun setPlayer(player: ExoPlayer) {
        playerView.player = player
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    onPositionChanged?.invoke(player.currentPosition)
                }
            }
        })
    }

    fun play() {
        playerView.player?.play()
    }

    fun pause() {
        playerView.player?.pause()
    }

    fun getCurrentPosition(): Long {
        return playerView.player?.currentPosition ?: 0L
    }

    fun setOnPositionChangedListener(listener: (Long) -> Unit) {
        onPositionChanged = listener
    }

    fun setOnFullscreenChangedListener(listener: (Boolean) -> Unit) {
        onFullscreenChanged = listener
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        playerView.player = null
    }
} 