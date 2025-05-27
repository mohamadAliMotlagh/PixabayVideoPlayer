package com.motlagh.feature.bookmark.domain

import com.motlagh.core.utils.result.resultOf
import com.motlagh.domain.video.VideoItemDomainModel
import kotlinx.coroutines.flow.Flow


fun interface BookmarksUseCase : suspend () -> Flow<List<VideoItemDomainModel>>


internal suspend fun bookmarksUseCaseImpl(
    repository: BookmarksRepository,
) = repository.getBookmarks()
