package com.tksko.mymovies.ui.movies_home.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.tksko.mymovies.databinding.ItemSearchResultBinding
import com.tksko.mymovies.domain.model.MovieResult

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val onClick: ((MovieResult) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieResult) = binding.apply {
        imageMoviesPoster.load(item.getPoster()) {
            crossfade(true)
            scale(Scale.FILL)
        }
        textName.text = item.name
        textGenre.text = item.genreName
        root.setOnClickListener {
            onClick?.invoke(item)
        }
    }
}