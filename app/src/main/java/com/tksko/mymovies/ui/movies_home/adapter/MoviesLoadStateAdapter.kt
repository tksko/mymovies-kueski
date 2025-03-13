package com.tksko.mymovies.ui.movies_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.tksko.mymovies.databinding.ItemMoviesLoadStateBinding
import com.tksko.mymovies.ui.movies_home.adapter.holder.LoadStateViewHolder

class MoviesLoadStateAdapter(
    private val onRetry: (() -> Unit)? = null
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val viewBinding =
            ItemMoviesLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding = viewBinding, onRetry = onRetry)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}