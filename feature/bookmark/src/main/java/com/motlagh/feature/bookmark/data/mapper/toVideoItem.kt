package com.motlagh.feature.bookmark.data.mapper

import com.motlagh.core.database.model.VideoEntity
import com.motlagh.domain.video.VideoItemDomainModel


/**
 * i have duplicated this function for other modules because its better to duplicated it.
 * because mapping could be different for different modules.
 * */

internal fun VideoEntity.toVideoItem() = VideoItemDomainModel(
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
