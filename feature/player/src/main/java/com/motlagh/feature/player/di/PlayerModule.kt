package com.motlagh.feature.player.di

import com.motlagh.feature.player.domain.PlayerRepository
import com.motlagh.feature.player.data.PlayerRepositoryImpl
import com.motlagh.feature.player.data.VideoDetailRepositoryImpl
import com.motlagh.feature.player.data.local.VideoDetailLocalDataSource
import com.motlagh.feature.player.data.local.VideoDetailLocalDataSourceImpl
import com.motlagh.feature.player.domain.VideoDetailRepository
import com.motlagh.feature.player.domain.VideoDetailUseCase
import com.motlagh.feature.player.domain.videoDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PlayerModule {

    @Binds
    @Singleton
    abstract fun bindPlayerRepository(
        playerRepositoryImpl: PlayerRepositoryImpl
    ): PlayerRepository

    @Binds
    abstract fun bindVideoDetailRepository(
        videoDetailRepositoryImpl: VideoDetailRepositoryImpl
    ): VideoDetailRepository

    @Binds
    abstract fun bindVideoDetailDataSource(
        videoDetailLocalDataSourceImpl: VideoDetailLocalDataSourceImpl
    ): VideoDetailLocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object VideoDetailProvider {

    @Provides
    internal fun videoUseCase(repository: VideoDetailRepository): VideoDetailUseCase =
        VideoDetailUseCase { videoDetailUseCaseImpl(it, repository) }
}