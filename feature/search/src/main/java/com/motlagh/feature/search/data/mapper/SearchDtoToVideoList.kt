package com.motlagh.feature.search.data.mapper

import com.motlagh.feature.search.data.remote.dto.SearchDTO
import com.motlagh.feature.search.domain.model.VideoItem


internal fun SearchDTO.toVideoItems() = hits?.map {
    VideoItem(
        videoUrl = it?.videos?.medium?.url ?: "",
        id = it?.id.toString(),
        tags = it?.tags?.split(",") ?: listOf(),
        views = it?.views ?: 0,
        likes = it?.likes ?: 0,
        username = it?.user ?: "",
        comments = it?.comments ?: 0,
        isBookmarked = false,
        thumbnailUrl = it?.videos?.medium?.thumbnail ?: ""
    )
} ?: listOf()

