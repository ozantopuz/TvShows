package com.ozantopuz.tvshows.data.entity

object TvShowResponseRawFactory {

    fun getTvShowResponseRaw() = TvShowResponseRaw(arrayListOf(TvShowFactory.getTvShow()))
}
