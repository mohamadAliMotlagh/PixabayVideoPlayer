package com.motlagh.feature.player.data.local

import com.motlagh.core.database.dao.VideoSearchDao
import com.motlagh.core.database.model.VideoEntity
import dagger.Provides
import jakarta.inject.Inject
import jakarta.inject.Singleton



class VideoDetailLocalDataSourceImpl @Inject constructor(
    private val videoSearchDao: VideoSearchDao
) : VideoDetailLocalDataSource {

    override suspend fun getVideoById(id: String) = videoSearchDao.getVideoById(id)
}