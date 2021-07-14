package com.ozantopuz.tvshows.domain.mapper

import com.ozantopuz.tvshows.data.entity.TvShowResponse
import com.ozantopuz.tvshows.data.entity.TvShowResponseViewItem
import com.ozantopuz.tvshows.util.mapper.Mapper

class TvShowViewItemMapper : Mapper<TvShowResponse, TvShowResponseViewItem> {

    override suspend fun map(item: TvShowResponse): TvShowResponseViewItem =
        TvShowResponseViewItem(item.tvShows)
}