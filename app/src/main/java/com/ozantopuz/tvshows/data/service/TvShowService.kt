package com.ozantopuz.tvshows.data.service

import com.ozantopuz.tvshows.data.entity.TvShowResponseRaw
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowService {

    @GET("tv/top_rated")
    suspend fun getTvShows(
        @Query("page") page: String
    ): TvShowResponseRaw
}