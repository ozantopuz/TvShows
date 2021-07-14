package com.ozantopuz.tvshows.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozantopuz.tvshows.R

class TvShowItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val size = parent.context.resources.getDimensionPixelSize(R.dimen.normal_100)
        outRect.set(size, size, size, 0)
    }
}