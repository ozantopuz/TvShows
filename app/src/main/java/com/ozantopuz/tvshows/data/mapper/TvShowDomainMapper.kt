package com.ozantopuz.tvshows.data.mapper

import com.ozantopuz.tvshows.data.entity.TvShowResponse
import com.ozantopuz.tvshows.data.entity.TvShowResponseRaw
import com.ozantopuz.tvshows.util.mapper.Mapper

class TvShowDomainMapper : Mapper<TvShowResponseRaw, TvShowResponse> {

    override suspend fun map(item: TvShowResponseRaw): TvShowResponse = TvShowResponse(item.results)
}