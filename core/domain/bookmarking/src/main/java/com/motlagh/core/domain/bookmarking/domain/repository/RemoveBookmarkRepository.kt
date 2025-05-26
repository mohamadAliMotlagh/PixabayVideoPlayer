package com.motlagh.core.domain.bookmarking.domain.repository

interface RemoveBookmarkRepository {
    suspend operator fun invoke(videoId: String)
}