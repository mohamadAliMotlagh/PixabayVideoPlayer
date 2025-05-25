package com.motlagh.feature.search.domain

import com.motlagh.core.utils.result.asResult
import com.motlagh.feature.search.domain.model.VideoItem
import kotlinx.coroutines.flow.Flow

fun interface SearchUseCase : suspend (String) -> Flow<Result<List<VideoItem>>>


internal suspend fun searchUseCaseImpl(
    query: String,
    repository: SearchVideoRepository,
): Flow<Result<List<VideoItem>>> = repository.searchVideos(query)
