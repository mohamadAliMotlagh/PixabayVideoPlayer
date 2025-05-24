package com.motlagh.feature.search.data

import android.util.Log
import com.motlagh.core.utils.coroutine.IoDispatcher
import com.motlagh.feature.search.data.local.VideoLocalDataSource
import com.motlagh.feature.search.data.mapper.toVideoEntity
import com.motlagh.feature.search.data.mapper.toVideoItem
import com.motlagh.feature.search.data.mapper.toVideoItems
import com.motlagh.feature.search.data.remote.SearchVideoRemoteDataSource
import com.motlagh.feature.search.domain.SearchVideoRepository
import com.motlagh.feature.search.domain.model.VideoItem
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch


class SearchVideoRepositoryImpl @Inject constructor(
    private val local: VideoLocalDataSource,
    private val remote: SearchVideoRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SearchVideoRepository {


    override fun searchVideos(query: String): Flow<Result<List<VideoItem>>> = channelFlow {

        launch(ioDispatcher) {
            remote.searchVideos(query).map {
                if ((it.hits?.size ?: 0) > 0) {
                    Log.e(
                        "TAGGGGGGG",
                        "ApiCall query:${query} ${it.toVideoItems().size} ${it.toVideoItems()[0].username} ${it.toVideoItems()[0].id}"
                    )
                }
                local.saveSearchResults(query, it.toVideoItems().map { it.toVideoEntity() })
            }.onFailure {
                Log.e("TAGGGGGGG", "ApiCall: query:${query}  ")

                send(Result.failure(it))
            }
        }

        local.getVideosForSearch(query).take(1).collect {
            Log.e(
                "TAGGGGGGG",
                "Databasse query:${query} ${it.size} ${it.map { Pair(it.id, it.username) }}"
            )

            send(Result.success(it.map { it.toVideoItem() }))
        }
    }
}