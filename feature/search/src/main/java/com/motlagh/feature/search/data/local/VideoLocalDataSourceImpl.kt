package com.motlagh.feature.search.data.local

import com.motlagh.core.database.dao.VideoSearchDao
import com.motlagh.core.database.model.SearchQueryEntity
import com.motlagh.core.database.model.SearchVideoCrossRef
import com.motlagh.core.database.model.VideoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoLocalDataSourceImpl @Inject constructor(private val dao: VideoSearchDao) :
    VideoLocalDataSource {
    override suspend fun saveSearchResults(query: String, videos: List<VideoEntity>) {
        dao.insertVideos(videos)

        val existing = dao.getSearchQueryByText(query)
        val searchId = existing?.id ?: dao.insertSearchQuery(SearchQueryEntity(query = query))

        val crossRefs =
            videos.map { video -> SearchVideoCrossRef(searchId = searchId, videoId = video.id) }
        dao.insertSearchVideoCrossRefs(crossRefs)
    }

    override fun getVideosForSearch(searchQuery: String): Flow<List<VideoEntity>> {
        return dao.getSearchWithVideosFlow(searchQuery)
            .map { it?.videos ?: emptyList() }
    }
}