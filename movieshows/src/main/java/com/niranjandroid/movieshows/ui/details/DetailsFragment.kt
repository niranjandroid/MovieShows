package com.niranjandroid.movieshows.ui.details

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import com.niranjandroid.movieshows.ui.base.PostersHorizontalAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

/**
 * Created by Niranjan P on 10/21/2017.
 */

class DetailsFragment : BaseAbstractFragment(), DetailsContract.View {

    private var movie : MovieModel ?= null

    @Inject
    override lateinit var presenter: DetailsContract.Presenter

    private var images : MutableList<String>? = ArrayList()

    @Inject
    @JvmField var posterHorizontalAdapter : PostersHorizontalAdapter? = null

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
        presenter.attachView(this)
        movie = Gson().fromJson(arguments?.getString(Constants.MOVIE_OBJ), MovieModel::class.java)
        updateData();
        movie?.backdropPath?.let{images?.add(movie?.backdropPath!!)}
        posterHorizontalAdapter?.init(images)
        listPosters.layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        listPosters.adapter = posterHorizontalAdapter
        presenter?.getMovieDetails(movie?.id)
    }

    override fun onFetchingMovieDetails(movieModel: MovieModel) {
        movie = movieModel
        updateData()
    }

    private fun updateData() {
        var title = movie?.title
        movie?.tagline?.let { title = "$title (${movie?.tagline})" }
        tvTitle?.text = title
        tvReleaseDate?.text = movie?.releaseDate
        tvRating?.text = String.format(activity.getString(R.string.info_rating),
                movie?.voteAverage, movie?.voteCount)

        tvGenres.text = presenter.getGenres(movie?.genres)
        tvSummary.text = movie?.overview
        movie?.images?.let { onFetchingImages(presenter.getImagesFromImageModel(movie?.images!!)) }
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseDetailsComponent()
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

    override fun onFetchingImages(images: MutableList<String>) {
        listPosters.visibility = View.VISIBLE
        this.images?.clear()
        this.images?.addAll(images)
        posterHorizontalAdapter?.notifyDataSetChanged()
    }
}