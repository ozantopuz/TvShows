package com.ozantopuz.tvshows.di

import com.ozantopuz.tvshows.data.service.TvShowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideTvShowService(retrofit: Retrofit): TvShowService = retrofit.create()
}