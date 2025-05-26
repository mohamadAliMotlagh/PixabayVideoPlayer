package com.motlagh.core.domain.bookmarking.domain

import com.motlagh.core.domain.bookmarking.domain.repository.AddBookmarkRepository
import com.motlagh.core.domain.bookmarking.domain.repository.RemoveBookmarkRepository


fun interface AddBookmarkUseCase : suspend (String) -> Unit

fun interface RemoveBookmarkUseCase : suspend (String) -> Unit


internal suspend fun removeBookmarkUseCaseImpl(
    videoID: String,
    repository: RemoveBookmarkRepository,
) {
    repository(videoID)
}


internal suspend fun addBookmarkUseCaseImpl(
    videoID: String,
    repository: AddBookmarkRepository,
) {
    repository(videoID)
}