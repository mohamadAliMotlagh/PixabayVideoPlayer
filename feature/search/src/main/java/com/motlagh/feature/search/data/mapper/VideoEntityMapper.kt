package com.motlagh.feature.search.data.mapper

import com.motlagh.core.database.model.VideoEntity
import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.data.remote.dto.Hit


internal fun VideoItemDomainModel.toVideoEntity() = VideoEntity(
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


fun VideoEntity.toVideoDomainModel() = VideoItemDomainModel(
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

