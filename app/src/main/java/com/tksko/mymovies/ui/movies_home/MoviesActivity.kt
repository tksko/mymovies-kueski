package com.tksko.mymovies.ui.movies_home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tksko.mymovies.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}