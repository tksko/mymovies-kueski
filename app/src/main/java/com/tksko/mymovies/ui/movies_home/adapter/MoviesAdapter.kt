package com.tksko.mymovies.ui.movies_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.tksko.mymovies.databinding.ItemMoviesBinding
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.ui.movies_home.adapter.holder.MovieViewHolder
import com.tksko.mymovies.utils.MoviesItemDiffer

class MoviesAdapter(
    private val onClick: ((MovieResult) -> Unit)? = null
) : PagingDataAdapter<MovieResult, MovieViewHolder>(MoviesItemDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewBinding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding = viewBinding, onClick = onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}