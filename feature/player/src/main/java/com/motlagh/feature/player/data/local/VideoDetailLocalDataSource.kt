package com.motlagh.feature.player.data.local

import com.motlagh.core.database.model.VideoEntity

interface VideoDetailLocalDataSource {
    suspend fun getVideoById(id: String): VideoEntity?
}