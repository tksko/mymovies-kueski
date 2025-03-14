package com.tksko.mymovies.ui.movies_home.adapter.holder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.tksko.mymovies.databinding.ItemSearchResultBinding
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.utils.yearOnly

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val onClick: ((MovieResult, View) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: MovieResult) = binding.apply {
        with(imageMoviesPoster) {
            transitionName = "${item.id}$TRANSITION_POSTFIX"
            load(item.getPoster()) {
                crossfade(true)
                scale(Scale.FILL)
            }
        }
        textName.text = item.name
        textMoreInfo.text = "${item.genreName.orEmpty()} - ${item.releaseDate?.yearOnly().orEmpty()}"
        root.setOnClickListener {
            onClick?.invoke(item, imageMoviesPoster)
        }
    }

    private companion object {
        const val TRANSITION_POSTFIX = "SEARCH"
    }
}