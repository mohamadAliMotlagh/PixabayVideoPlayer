package com.motlagh.feature.search.domain

import com.motlagh.domain.video.VideoItemDomainModel
import kotlinx.coroutines.flow.Flow

interface SearchVideoRepository {
   suspend fun searchVideos(query: String): Flow<Result<List<VideoItemDomainModel>>>
}