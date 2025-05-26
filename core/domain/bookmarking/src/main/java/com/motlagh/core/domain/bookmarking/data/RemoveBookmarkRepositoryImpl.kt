package com.motlagh.core.domain.bookmarking.data

import com.motlagh.core.domain.bookmarking.data.local.BookmarkLocalDataSource
import com.motlagh.core.domain.bookmarking.domain.repository.RemoveBookmarkRepository
import jakarta.inject.Inject

class RemoveBookmarkRepositoryImpl @Inject constructor(
    private val bookmarkLocalDataSource: BookmarkLocalDataSource
) : RemoveBookmarkRepository {
    override suspend fun invoke(videoId: String) {
        bookmarkLocalDataSource.remove(videoId)
    }
}