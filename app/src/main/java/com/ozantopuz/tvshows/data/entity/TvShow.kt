package com.ozantopuz.tvshows.data.entity

import com.google.gson.annotations.SerializedName

data class TvShow(
    val id: Int? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Float? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)