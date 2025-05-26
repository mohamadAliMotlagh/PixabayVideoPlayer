package com.motlagh.feature.player.presenter

sealed interface PlayerIntent {
    object TogglePlayPause : PlayerIntent
    object ToggleFullscreen : PlayerIntent
}