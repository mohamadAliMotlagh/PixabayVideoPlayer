package com.motlagh.core.domain.bookmarking.domain.repository

internal interface RemoveBookmarkRepository {
    suspend fun removeBookmark(videoId: String)
}