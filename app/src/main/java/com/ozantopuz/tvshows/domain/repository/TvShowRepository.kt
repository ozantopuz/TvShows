package com.ozantopuz.tvshows.domain.repository

import com.ozantopuz.tvshows.data.entity.TvShowResponse

interface TvShowRepository {
    suspend fun fetchTvShows(page: String): TvShowResponse

}