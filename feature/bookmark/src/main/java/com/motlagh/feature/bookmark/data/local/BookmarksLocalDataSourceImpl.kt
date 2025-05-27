package com.motlagh.feature.bookmark.data.local

import com.motlagh.core.database.dao.VideoSearchDao
import com.motlagh.core.database.model.VideoEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class BookmarksLocalDataSourceImpl @Inject constructor(private val dao: VideoSearchDao) :
    BookmarksLocalDataSource {
    override suspend fun getAllBookmarks(): Flow<List<VideoEntity>> = dao.getAllBookmarkedVideos()
}