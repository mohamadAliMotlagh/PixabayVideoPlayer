package com.motlagh.feature.player.data.repository

import androidx.media3.exoplayer.ExoPlayer

interface PlayerRepository {
    fun getPlayer(): ExoPlayer
    fun updateVideoUrl(url: String)
    fun setOnPositionChangedListener(listener: (Long) -> Unit)
    fun setPlaybackState(position: Long, shouldPlay: Boolean)
    fun release()
} 