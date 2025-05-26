package com.motlagh.core.domain.bookmarking.data

import com.motlagh.core.domain.bookmarking.data.local.BookmarkLocalDataSource
import com.motlagh.core.domain.bookmarking.domain.repository.AddBookmarkRepository
import jakarta.inject.Inject

class AddBookmarkRepositoryImpl @Inject constructor(
    private val bookmarkLocalDataSource: BookmarkLocalDataSource
) : AddBookmarkRepository {
    override suspend fun invoke(videoId: String) {
        bookmarkLocalDataSource.add(videoId)
    }
}