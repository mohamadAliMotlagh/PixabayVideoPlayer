package com.motlagh.feature.search.presenter.mapper

import com.motlagh.feature.search.domain.model.VideoItem
import com.motlagh.feature.search.presenter.SearchUiState
import com.motlagh.feature.search.presenter.Video


internal fun VideoItem.toUIModel() = Video(
    videoUrl = videoUrl,
    id = id,
    userid = username,
    thumbnailUrl = thumbnailUrl,
    tags = tags,
    username = username
)