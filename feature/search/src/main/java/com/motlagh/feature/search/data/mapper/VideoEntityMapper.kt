package com.motlagh.feature.search.data.mapper

import com.motlagh.core.database.model.VideoEntity
import com.motlagh.core.utils.mapper.Mapper
import com.motlagh.feature.search.data.remote.dto.SearchDTO
import com.motlagh.feature.search.domain.model.VideoItem


internal fun VideoItem.toVideoEntity() = VideoEntity(
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


fun VideoEntity.toVideoItem() = VideoItem(
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

