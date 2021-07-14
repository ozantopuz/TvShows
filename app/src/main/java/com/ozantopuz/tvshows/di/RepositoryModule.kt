package com.ozantopuz.tvshows.di

import com.ozantopuz.tvshows.domain.repository.TvShowRepository
import com.ozantopuz.tvshows.domain.repository.TvShowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTvShowRepository(
        repository: TvShowRepositoryImpl
    ): TvShowRepository
}