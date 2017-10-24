package com.niranjandroid.movieshows.ui.listing

import com.niranjandroid.movieshows.data.model.MovieModel

/**
 * Created by Niranjan P on 10/23/2017.
 */

interface ItemClickListener {
    fun onMoreBtnClicked()
    fun onMovieSelected(movieModel: MovieModel?)
}
