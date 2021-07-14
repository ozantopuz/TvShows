package com.ozantopuz.tvshows.data.entity

object TvShowResponseViewItemFactory {

    fun getTvShowResponseViewItem() = TvShowResponseViewItem(arrayListOf(TvShowFactory.getTvShow()))
}
