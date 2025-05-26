package com.motlagh.feature.search.presenter.mapper

import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.presenter.Video


internal fun VideoItemDomainModel.toUIModel() = Video(
    videoUrl = videoUrl,
    id = id,
    userid = username,
    thumbnailUrl = thumbnailUrl,
    tags = tags,
    username = username
)