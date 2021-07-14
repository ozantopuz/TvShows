package com.ozantopuz.tvshows.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozantopuz.tvshows.databinding.ItemTvShowBinding
import com.ozantopuz.tvshows.data.entity.TvShow
import com.ozantopuz.tvshows.util.extension.loadImage

class TvShowAdapter(
    private var list: ArrayList<TvShow> = arrayListOf()
) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemBinding =
            ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val item: TvShow = list[position]
        with(holder.itemBinding) {
            imageView.loadImage(item.posterPath)
            textViewTitle.text = item.originalName
        }
    }

    override fun getItemCount() = list.size

    fun setList(list: ArrayList<TvShow>) {
        this.list = list
        notifyDataSetChanged()
    }

    class TvShowViewHolder(val itemBinding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}