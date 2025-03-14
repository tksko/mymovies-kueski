package com.tksko.mymovies.ui.movies_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.appbar.AppBarLayout
import com.tksko.mymovies.databinding.FragmentMoviesDetailsBinding
import com.tksko.mymovies.utils.grayScale
import com.tksko.mymovies.utils.viewBinding
import com.tksko.mymovies.utils.yearOnly
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import com.tksko.mymovies.R

@AndroidEntryPoint
class MoviesDetailsFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private val binding by viewBinding {
        FragmentMoviesDetailsBinding.inflate(layoutInflater)
    }

    private val args: MoviesDetailsFragmentArgs by navArgs()

    private val emptyOverview by lazy {
        getString(R.string.movie_details_screen_no_overview)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonBack.setOnClickListener {
                goBack()
            }
            with(args.movieResult) {
                imageMoviesPoster.apply {
                    transitionName = args.transitionName
                    load(getPoster()) {
                        listener(
                            onSuccess = { _, _ ->
                                startPostponedEnterTransition()
                            },
                            onError = { _, _ ->
                                startPostponedEnterTransition()
                            }
                        )
                    }
                }
                textName.text = name
                textMoreInfo.text = "${genreName.orEmpty()} - ${releaseDate?.yearOnly().orEmpty()}"
                textOverview.text = overview?.ifBlank { emptyOverview } ?: emptyOverview
                with(imageBackground) {
                    load(getBackdrop()) {
                        crossfade(true)
                        crossfade(5000)
                    }
                    grayScale()
                }
            }
            appBarLayout.addOnOffsetChangedListener(this@MoviesDetailsFragment)
        }
    }

    private fun goBack() = requireActivity().onBackPressedDispatcher.onBackPressed()

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val percentage: Int =
            (abs(verticalOffset)) * 100 / (appBarLayout?.getTotalScrollRange() ?: 1)
        binding.imageMoviesPoster.scaleX = 1 - (percentage / 100f)
        binding.imageMoviesPoster.scaleY = 1 - (percentage / 100f)
    }
}