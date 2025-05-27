package com.motlagh.feature.bookmark.presenter.mapper

import com.motlagh.core.ui.videoItem.VideoItemUiModel
import com.motlagh.domain.video.VideoItemDomainModel


internal fun VideoItemDomainModel.toUIModel() = VideoItemUiModel(
    id = id,
    username = username,
    thumbnailUrl = thumbnailUrl,
    tags = tags,
    isBookmarked = isBookmarked
)