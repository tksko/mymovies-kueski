package com.tksko.mymovies.ui.movies_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.google.android.material.appbar.AppBarLayout
import com.tksko.mymovies.databinding.FragmentMoviesDetailsBinding
import com.tksko.mymovies.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date
import kotlin.math.abs

@AndroidEntryPoint
class MoviesDetailsFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private val binding by viewBinding {
        FragmentMoviesDetailsBinding.inflate(layoutInflater)
    }

    private val args: MoviesDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonBack.setOnClickListener {
                goBack()
            }
            with(args.movieResult) {
                imageMoviesPoster.load(getPoster())
                textName.text = name
                textMoreInfo.text = "$genreName - ${getReleaseYear(releaseDate)}"
                textOverview.text = overview
                with(imageBackground) {
                    load(getBackdrop()) {
                        crossfade(true)
                        scale(Scale.FILL)
                    }
                }
            }
            appBarLayout.addOnOffsetChangedListener(this@MoviesDetailsFragment)
        }
    }

    private fun getReleaseYear(releaseDate: Date?) = releaseDate?.let {
        val calendar = Calendar.getInstance()
        calendar.time = releaseDate
        calendar.get(Calendar.YEAR).toString()
    } ?: ""

    private fun goBack() = requireActivity().onBackPressedDispatcher.onBackPressed()

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val percentage: Int =
            (abs(verticalOffset)) * 100 / (appBarLayout?.getTotalScrollRange() ?: 1)
        binding.imageMoviesPoster.scaleX = 1 - (percentage / 100f)
        binding.imageMoviesPoster.scaleY = 1 - (percentage / 100f)
    }
}