package com.motlagh.feature.player.data.mapper

import com.motlagh.core.database.model.VideoEntity
import com.motlagh.domain.video.VideoItemDomainModel

fun VideoEntity.toVideoItem() = VideoItemDomainModel(
    id = id,
    thumbnailUrl = thumbnailUrl,
    videoUrl = videoUrl,
    tags = tags,
    username = username,
    views = views,
    likes = likes,
    comments = comments,
    isBookmarked = isBookmarked
)