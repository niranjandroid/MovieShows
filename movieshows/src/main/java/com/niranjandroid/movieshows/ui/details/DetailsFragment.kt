package com.niranjandroid.movieshows.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.network.ApiMedia
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import javax.inject.Inject

/**
 * Created by Niranjan P on 10/21/2017.
 */

class DetailsFragment : BaseAbstractFragment(){

    private var movie : MovieModel ?= null

    private var imgMovie: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvReleaseDate: TextView? = null
    private var tvRating: TextView? = null

    @Inject
    override lateinit var presenter: DetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_details, container, false)
    }

    override fun init() {
        (activity.application as App).getDetailsComponent()?.inject(this)
        movie = Gson().fromJson(arguments?.getString(Constants.MOVIE_OBJ), MovieModel::class.java)
        initViews();
        updateData();
    }

    private fun initViews() {
        imgMovie = activity?.findViewById<View>(R.id.img_movie) as ImageView
        tvTitle = activity?.findViewById<View>(R.id.tv_title) as TextView
        tvReleaseDate = activity?.findViewById<View>(R.id.tv_release_date) as TextView
        tvRating = activity?.findViewById<View>(R.id.tv_rating) as TextView
    }

    private fun updateData() {
        Glide.with(activity).load(ApiMedia.getPosterPath(movie?.posterPath)).into(imgMovie)
        tvTitle?.text = movie?.title
        tvReleaseDate?.text = movie?.releaseDate
        tvRating?.text = String.format(activity.getString(R.string.info_rating),
                movie?.voteAverage, movie?.voteCount)

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseMovieListingComponent()
    }

    companion object {
        fun newInstance(movie : String?): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(Constants.MOVIE_OBJ, movie)
            detailsFragment.arguments = bundle
            return detailsFragment
        }
    }
}