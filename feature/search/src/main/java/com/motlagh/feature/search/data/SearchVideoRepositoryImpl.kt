package com.motlagh.feature.search.data

import android.util.Log
import com.motlagh.core.utils.coroutine.IoDispatcher
import com.motlagh.core.utils.coroutine.IoScope
import com.motlagh.domain.video.VideoItemDomainModel
import com.motlagh.feature.search.data.local.VideoLocalDataSource
import com.motlagh.feature.search.data.mapper.toVideoEntity
import com.motlagh.feature.search.data.mapper.toVideoItem
import com.motlagh.feature.search.data.mapper.toVideoItems
import com.motlagh.feature.search.data.remote.SearchVideoRemoteDataSource
import com.motlagh.feature.search.domain.SearchVideoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch


class SearchVideoRepositoryImpl @Inject constructor(
    private val local: VideoLocalDataSource,
    private val remote: SearchVideoRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @IoScope private val coroutineScope: CoroutineScope
) : SearchVideoRepository {

    init {
        Log.e(
            "TAGGGGGGG",
            "INIT CALLED"
        )

    }

    override suspend fun searchVideos(query: String): Flow<Result<List<VideoItemDomainModel>>> = channelFlow {
        //_query.value = query

        ensureActive()
        launch(ioDispatcher) {
            searchFromApi(query)
        }

        launch {
            local.searchInDatabase(query)
                .cancellable()
                .onCompletion {
                    val isCancelled = (it is CancellationException)
                    if (isCancelled) {
                        Log.e(
                            "TAGGGGGGG",
                            "CANCELED in repository "
                        )
                    }
                }.flowOn(ioDispatcher).collect {
                    Log.e(
                        "TAGGGGGGG",
                        "Databasse query:${query} ${it.size} ${it.map { Pair(it.id, it.username) }}"
                    )
                    send(Result.success(it.map { it.toVideoItem() }))
                }

        }

        awaitClose() {
            Log.e(
                "TAGGGGGGG",
                "awaitClose CALLED"
            )
        }

    }.cancellable()


    private suspend fun searchFromApi(query: String) {
        delay(5000)
        remote.searchVideos(query).map {
            if ((it.hits?.size ?: 0) > 0) {
                Log.e(
                    "TAGGGGGGG",
                    "ApiCall query:${query} ${it.toVideoItems().size} ${it.toVideoItems()[0].username} ${it.toVideoItems()[0].id}"
                )
            }
            local.saveSearchResults(query, it.toVideoItems().map { it.toVideoEntity() })
        }
    }
}