package com.niranjandroid.movieshows.ui.listing

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import com.niranjandroid.movieshows.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.content_movie_listing.*
import javax.inject.Inject


/**
 * Created by Niranjan P on 10/21/2017.
 */

class ListingFragment : BaseAbstractFragment(), ListingContract.View, ItemClickListener {


    @Inject
    @JvmField var listingAdapter: ListingAdapter? = null

    var mMovieListingModel: MovieListModel? = null

    var mMovieList : MutableList<MovieModel> ?= ArrayList()

    @Inject
    override lateinit var presenter: ListingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_movie_listing, container, false)
    }

    override fun init() {
        (activity.application as App).getMovieListingComponent()?.inject(this)
        presenter?.attachView(this)
        presenter?.initMoviesList();
        listingAdapter?.init(mMovieList, this)
        listMovies.layoutManager = GridLayoutManager(activity, 3)
        listMovies.adapter = listingAdapter
    }

    override fun onFetchingMovies(movieListModel: MovieListModel) {
        mMovieList?.clear()
        updateMovies(movieListModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseDetailsComponent()
    }

    companion object {
        fun newInstance(): ListingFragment {
            return ListingFragment()
        }
    }

    override fun onMoreBtnClicked() {
        (mMovieListingModel?.page?.plus(1))?.toInt()?.let { presenter.loadMovies(it) }
    }

    override fun onMovieSelected(movieModel: MovieModel?) {
        var intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra(Constants.MOVIE_OBJ, Gson().toJson(movieModel, MovieModel::class.java))
        startActivity(intent)
    }

    override fun updateMovies(movieListModel: MovieListModel) {
        this.mMovieListingModel = movieListModel
        movieListModel?.results?.let { this.mMovieList?.addAll(it) }
        listingAdapter?.notifyDataSetChanged()
    }
}
