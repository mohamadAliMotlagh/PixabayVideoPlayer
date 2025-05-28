package com.motlagh.core.domain.bookmarking.data.local

import com.motlagh.core.database.dao.VideoSearchDao
import jakarta.inject.Inject


/**
this could be in another modula.
*/


internal class BookmarkLocalDataSourceImpl
@Inject constructor(
    private val dao: VideoSearchDao
) : BookmarkLocalDataSource {


    override suspend fun add(videoID: String) {
        dao.bookmarkVideo(videoID)
    }

    override suspend fun remove(videoID: String) {
        dao.removeBookmarkVideo(videoID)
    }
}