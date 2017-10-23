package com.niranjandroid.movieshows.ui.listing

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import javax.inject.Inject



/**
 * Created by Niranjan P on 10/21/2017.
 */

class ListingFragment : BaseAbstractFragment(), ListingContract.View {

    var listMovies: RecyclerView? = null

    var listingAdapter: ListingAdapter? = null

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
        listingAdapter = ListingAdapter(mMovieList)
        listMovies = activity.findViewById<View>(R.id.list_movies) as RecyclerView
        listMovies?.layoutManager = GridLayoutManager(activity, 3)
        listMovies?.adapter = listingAdapter
    }

    override fun updateMovies(movieListModel: MovieListModel) {
        mMovieList?.clear()
        movieListModel.results?.let { mMovieList?.addAll(it) }
        listingAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseMovieListingComponent()
    }

    companion object {
        fun newInstance(): ListingFragment {
            return ListingFragment()
        }
    }
}
