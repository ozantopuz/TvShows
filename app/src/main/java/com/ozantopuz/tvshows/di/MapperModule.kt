package com.ozantopuz.tvshows.di

import com.ozantopuz.tvshows.data.mapper.TvShowDomainMapper
import com.ozantopuz.tvshows.domain.mapper.TvShowViewItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun providesTvShowDomainMapper(): TvShowDomainMapper = TvShowDomainMapper()

    @Provides
    fun providesTvShowViewItemMapper(): TvShowViewItemMapper = TvShowViewItemMapper()
}