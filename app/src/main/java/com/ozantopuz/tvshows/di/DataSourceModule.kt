package com.ozantopuz.tvshows.di

import com.ozantopuz.tvshows.data.datasource.TvShowRemoteDataSource
import com.ozantopuz.tvshows.data.datasource.TvShowRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindTvShowRemoteDataSource(
        remoteDataSource: TvShowRemoteDataSourceImpl
    ): TvShowRemoteDataSource
}