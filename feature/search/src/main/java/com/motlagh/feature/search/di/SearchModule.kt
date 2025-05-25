package com.motlagh.feature.search.di

import com.motlagh.feature.search.data.SearchVideoRepositoryImpl
import com.motlagh.feature.search.data.local.VideoLocalDataSource
import com.motlagh.feature.search.data.local.VideoLocalDataSourceImpl
import com.motlagh.feature.search.data.network.SearchApi
import com.motlagh.feature.search.data.remote.SearchVideoRemoteDataSource
import com.motlagh.feature.search.data.remote.SearchVideoRemoteDataSourceImpl
import com.motlagh.feature.search.domain.SearchUseCase
import com.motlagh.feature.search.domain.SearchVideoRepository
import com.motlagh.feature.search.domain.searchUseCaseImpl
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
abstract class SearchModule {

    @Binds
    internal abstract fun localDataSource(
        localDataSource: VideoLocalDataSourceImpl,
    ): VideoLocalDataSource

    @Binds
    internal abstract fun remoteDataSource(
        remoteDataSource: SearchVideoRemoteDataSourceImpl,
    ): SearchVideoRemoteDataSource

    @Binds
    internal abstract fun repository(
        repository: SearchVideoRepositoryImpl,
    ): SearchVideoRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvider {
    @Provides
    @Singleton
    internal fun provideApiCalls(retrofit: Retrofit): SearchApi = retrofit.create()

    @Provides
    internal fun searchUseCase(repository: SearchVideoRepository): SearchUseCase =
        SearchUseCase { searchUseCaseImpl(it, repository) }
}