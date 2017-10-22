package com.niranjandroid.movieshows.ui.movielisting

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import javax.inject.Inject

/**
 * Created by Niranjan P on 10/21/2017.
 */

class MovieListingFragment : BaseAbstractFragment(), MovieListingContract.View {

    @BindView(R.id.list_movies)
    val listMovies: RecyclerView? = null

    var movieListingAdapter: MovieListingAdapter? = null

    var mMovieListingModel: MovieListModel? = null
    @Inject
    override var presenter: MovieListingContract.Presenter? = null

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
        movieListingAdapter = MovieListingAdapter(mMovieListingModel)
        listMovies?.layoutManager = LinearLayoutManager(activity)
        listMovies?.adapter = movieListingAdapter
    }

    override fun updateMovies(movieListModel: MovieListModel) {
        movieListingAdapter?.updateMovies(movieListModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseMovieListingComponent()
    }

    companion object {
        fun newInstance(): MovieListingFragment {
            return MovieListingFragment()
        }
    }
}
