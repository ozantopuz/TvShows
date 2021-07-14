package com.ozantopuz.tvshows.di

import com.ozantopuz.tvshows.domain.usecase.TvShowUseCase
import com.ozantopuz.tvshows.util.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindTvShowUseCase(
        useCase: TvShowUseCase
    ): UseCase
}