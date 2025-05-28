package com.motlagh.feature.search.data.filter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet


/**
 * i have implemented filters with this way
 * because with this approach we can add more filters in future.(Open Close Principle)
 *
 * we can use IntoMap or normal way like my (Coroutine Dispatcher provider) .
 * */

@Module
@InstallIn(SingletonComponent::class)
object FilterModule {

    @Provides
    @IntoSet
   internal fun provideDurationFilter(): VideoFilter = DurationVideoFilter(60)
}