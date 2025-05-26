package com.motlagh.core.domain.bookmarking.domain.repository

internal interface AddBookmarkRepository {
    suspend operator fun invoke(videoId: String)
}