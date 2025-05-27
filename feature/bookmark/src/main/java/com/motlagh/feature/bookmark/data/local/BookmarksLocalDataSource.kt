package com.motlagh.feature.bookmark.data.local

import com.motlagh.core.database.model.VideoEntity
import kotlinx.coroutines.flow.Flow

internal interface BookmarksLocalDataSource {
    suspend fun getAllBookmarks(): Flow<List<VideoEntity>>
}