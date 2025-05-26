package com.motlagh.core.domain.bookmarking.data.local

import com.motlagh.core.database.model.VideoEntity

interface BookmarkLocalDataSource {
    suspend fun add(videoID: String)
    suspend fun remove(videoID: String)
}