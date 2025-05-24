package com.motlagh.feature.search.data.local

import com.motlagh.core.database.model.VideoEntity
import kotlinx.coroutines.flow.Flow

interface VideoLocalDataSource {
    suspend fun saveSearchResults(query: String, videos: List<VideoEntity>)
    fun getVideosForSearch(searchQuery: String): Flow<List<VideoEntity>>
}