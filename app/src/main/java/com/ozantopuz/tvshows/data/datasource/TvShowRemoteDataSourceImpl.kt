package com.ozantopuz.tvshows.data.datasource

import com.ozantopuz.tvshows.data.mapper.TvShowDomainMapper
import com.ozantopuz.tvshows.data.service.TvShowService
import com.ozantopuz.tvshows.data.entity.TvShowResponse
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(
    private val service: TvShowService,
    private val mapper: TvShowDomainMapper
) : TvShowRemoteDataSource {

    override suspend fun fetchTvShows(page: String): TvShowResponse {
        val response = service.getTvShows(page)
        return mapper.map(response)
    }

}