package com.ozantopuz.tvshows.domain.repository

import com.ozantopuz.tvshows.data.datasource.TvShowRemoteDataSource
import com.ozantopuz.tvshows.data.entity.TvShowResponse
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {

    override suspend fun fetchTvShows(page: String): TvShowResponse =
        remoteDataSource.fetchTvShows(page)
}