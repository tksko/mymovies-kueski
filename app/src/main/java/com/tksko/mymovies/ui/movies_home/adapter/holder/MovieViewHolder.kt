package com.tksko.mymovies.ui.movies_home.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.tksko.mymovies.databinding.ItemMoviesBinding
import com.tksko.mymovies.domain.model.MovieResult

class MovieViewHolder(
    private val binding: ItemMoviesBinding,
    private val onClick: ((MovieResult, View) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieResult) = binding.apply {
        with(item) {
            textName.text = name
            textGenre.text = genreName.orEmpty()
            with(imageMoviesPoster) {
                transitionName = item.id.toString()
                load(getBackdrop()) {
                    crossfade(true)
                    scale(Scale.FILL)
                }
            }
            root.setOnClickListener {
                onClick?.invoke(this, imageMoviesPoster)
            }
        }
    }
}