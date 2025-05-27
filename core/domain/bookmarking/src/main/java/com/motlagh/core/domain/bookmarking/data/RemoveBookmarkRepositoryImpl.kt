package com.motlagh.core.domain.bookmarking.data

import com.motlagh.core.domain.bookmarking.data.local.BookmarkLocalDataSource
import com.motlagh.core.domain.bookmarking.domain.repository.RemoveBookmarkRepository
import jakarta.inject.Inject

internal class RemoveBookmarkRepositoryImpl @Inject constructor(
    private val bookmarkLocalDataSource: BookmarkLocalDataSource
) : RemoveBookmarkRepository {
    override suspend fun removeBookmark(videoId: String) {
        bookmarkLocalDataSource.remove(videoId)
    }
}