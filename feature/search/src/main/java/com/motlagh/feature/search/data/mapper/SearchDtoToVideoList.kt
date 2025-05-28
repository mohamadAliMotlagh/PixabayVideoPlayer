package com.motlagh.feature.search.data.mapper

import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.data.remote.dto.Hit
import com.motlagh.feature.search.data.remote.dto.SearchDTO


internal fun Hit.toVideoDomainModel() = with(this) {
    VideoItemDomainModel(
        videoUrl = videos?.medium?.url ?: "",
        id = id.toString(),
        tags = tags?.split(",") ?: listOf(),
        views = views ?: 0,
        likes = likes ?: 0,
        username = user ?: "",
        comments = comments ?: 0,
        isBookmarked = false,
        thumbnailUrl = videos?.medium?.thumbnail ?: ""
    )
}






