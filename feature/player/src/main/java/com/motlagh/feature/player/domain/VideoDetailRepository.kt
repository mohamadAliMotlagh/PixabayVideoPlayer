package com.motlagh.feature.player.domain

import com.motlagh.domain.video.VideoItemDomainModel

interface VideoDetailRepository {
    suspend fun getVideoById(id: String): VideoItemDomainModel?
}