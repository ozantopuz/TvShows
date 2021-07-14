package com.ozantopuz.tvshows.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ozantopuz.tvshows.R

fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(BASE_IMAGE_URL + url)
            .into(this)
    } ?: kotlin.run {
        setImageResource(R.drawable.ic_placeholder)
    }
}

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"