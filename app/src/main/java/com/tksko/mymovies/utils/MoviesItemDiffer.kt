package com.tksko.mymovies.utils

import androidx.recyclerview.widget.DiffUtil
import com.tksko.mymovies.domain.model.MovieResult

object MoviesItemDiffer : DiffUtil.ItemCallback<MovieResult>() {
    override fun areItemsTheSame(
        oldItem: MovieResult,
        newItem: MovieResult
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieResult,
        newItem: MovieResult
    ): Boolean {
        return oldItem == newItem
    }
}