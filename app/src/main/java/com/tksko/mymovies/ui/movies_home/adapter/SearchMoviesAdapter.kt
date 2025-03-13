package com.tksko.mymovies.ui.movies_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tksko.mymovies.databinding.ItemSearchResultBinding
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.ui.movies_home.adapter.holder.SearchResultViewHolder
import com.tksko.mymovies.utils.MoviesItemDiffer

class SearchMoviesAdapter(
    private val onClick: ((MovieResult) -> Unit)? = null
) : ListAdapter<MovieResult, SearchResultViewHolder>(MoviesItemDiffer) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {
        val viewBinding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding = viewBinding, onClick = onClick)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}