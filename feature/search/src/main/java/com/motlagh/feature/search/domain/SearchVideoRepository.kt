package com.motlagh.feature.search.domain

import com.motlagh.feature.search.domain.model.VideoItem
import kotlinx.coroutines.flow.Flow

interface SearchVideoRepository {
   suspend fun searchVideos(query: String): Flow<Result<List<VideoItem>>>
}