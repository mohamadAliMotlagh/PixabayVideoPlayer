package com.motlagh.core.domain.bookmarking.domain.repository

internal interface AddBookmarkRepository {
    suspend fun addBookmark(videoId: String)
}