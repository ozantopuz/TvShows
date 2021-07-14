package com.ozantopuz.tvshows.data.datasource

import com.ozantopuz.tvshows.data.entity.TvShowResponse

interface TvShowRemoteDataSource {
    suspend fun fetchTvShows(page: String): TvShowResponse
}