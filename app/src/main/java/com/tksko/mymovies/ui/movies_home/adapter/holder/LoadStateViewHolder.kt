package com.tksko.mymovies.ui.movies_home.adapter.holder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.tksko.mymovies.databinding.ItemMoviesLoadStateBinding

class LoadStateViewHolder(
    private val binding: ItemMoviesLoadStateBinding,
    private val onRetry: (() -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(state: LoadState) = binding.apply {
        buttonRetry.setOnClickListener { onRetry?.invoke() }
        viewLoading.isVisible = state is LoadState.Loading
        textMessage.isVisible = state is LoadState.Error
        buttonRetry.isVisible = onRetry != null && state is LoadState.Error
    }
}