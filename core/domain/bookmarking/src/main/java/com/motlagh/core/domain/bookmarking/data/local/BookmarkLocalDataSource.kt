package com.motlagh.core.domain.bookmarking.data.local

internal interface BookmarkLocalDataSource {
    suspend fun add(videoID: String)
    suspend fun remove(videoID: String)
}