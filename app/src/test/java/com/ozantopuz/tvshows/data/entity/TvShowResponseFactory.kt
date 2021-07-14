package com.ozantopuz.tvshows.data.entity

object TvShowResponseFactory {

    fun getTvShowResponse() = TvShowResponse(arrayListOf(TvShowFactory.getTvShow()))
}
