package com.motlagh.feature.search.domain

import com.motlagh.feature.search.domain.model.VideoItem
import kotlinx.coroutines.flow.Flow

interface SearchVideoRepository {
    fun searchVideos(query: String): Flow<Result<List<VideoItem>>>
}