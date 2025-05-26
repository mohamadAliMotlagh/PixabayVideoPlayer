package com.motlagh.feature.player.data

import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.player.data.local.VideoDetailLocalDataSource
import com.motlagh.feature.player.data.mapper.toVideoItem
import com.motlagh.feature.player.domain.VideoDetailRepository
import jakarta.inject.Inject

class VideoDetailRepositoryImpl
@Inject constructor(private val videoDetailLocalDataSource: VideoDetailLocalDataSource) :
    VideoDetailRepository {
    override suspend fun getVideoById(id: String): VideoItemDomainModel? {
        return videoDetailLocalDataSource.getVideoById(id)?.toVideoItem()
    }
}

