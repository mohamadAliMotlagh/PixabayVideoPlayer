package com.motlagh.feature.bookmark.domain

import com.motlagh.domain.video.VideoItemDomainModel
import kotlinx.coroutines.flow.Flow

internal interface BookmarksRepository {
    suspend fun getBookmarks(): Flow<List<VideoItemDomainModel>>}