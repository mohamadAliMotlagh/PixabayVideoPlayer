package com.motlagh.feature.search.domain

import com.motlagh.domain.video.VideoItemDomainModel
import kotlinx.coroutines.flow.Flow

fun interface SearchUseCase : suspend (String) -> Flow<Result<List<VideoItemDomainModel>>>

`
internal suspend fun searchUseCaseImpl(
    query: String,
    repository: SearchVideoRepository,
): Flow<Result<List<VideoItemDomainModel>>> = repository.searchVideos(query)
