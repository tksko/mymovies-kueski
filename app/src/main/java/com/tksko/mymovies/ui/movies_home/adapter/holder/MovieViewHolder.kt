package com.tksko.mymovies.ui.movies_home.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.tksko.mymovies.databinding.ItemMoviesBinding
import com.tksko.mymovies.domain.model.MovieResult

class MovieViewHolder(
    private val binding: ItemMoviesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieResult) = binding.apply {
        with(item) {
            textName.text = name
            textGenre.text = genreName
            with(imageMoviesPoster) {
                load(getBackdrop()) {
                    crossfade(true)
                    scale(Scale.FILL)
                }
            }
        }
    }
}