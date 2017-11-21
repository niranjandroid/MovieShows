package com.niranjandroid.movieshows.ui.movielisting

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.Button
import android.widget.ImageView
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
import javax.inject.Named


/**
 * Created by Niranjan P on 10/21/2017.
 */

class ListingFragment : BaseAbstractFragment(), ListingContract.View, ItemClickListener {


    @Inject
    @JvmField
    var listingAdapter: ListingAdapter? = null

    var mMovieListingModel: MovieListModel? = null

    var mMovieList: MutableList<MovieModel>? = ArrayList()

    @Inject
    @field:Named("popular")
    lateinit var popularPresenter: ListingContract.Presenter

    @Inject
    @field:Named("now_playing")
    lateinit var nowPlayingPresenter: ListingContract.Presenter

    @Inject
    @field:Named("upcoming")
    lateinit var upcomingPresenter: ListingContract.Presenter

    @Inject
    @field:Named("top_rated")
    lateinit var topRatedPresenter: ListingContract.Presenter

    override var presenter: ListingContract.Presenter? = null

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
        presenter = popularPresenter;
        presenter?.attachView(this)
        presenter?.initMoviesList();
        listingAdapter?.init(mMovieList, this)
        listMovies.layoutManager = GridLayoutManager(activity, 3)
        listMovies.adapter = listingAdapter
        setHasOptionsMenu(true)
    }

    override fun onFetchingMovies(movieListModel: MovieListModel) {
        mMovieList?.clear()
        updateMovies(movieListModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseMovieListingComponent()
        mMovieListingModel = null
        mMovieList = null
    }

    companion object {
        fun newInstance(): ListingFragment {
            return ListingFragment()
        }
    }

    override fun onMoreBtnClicked() {
        (mMovieListingModel?.page?.plus(1))?.toInt()?.let { presenter?.loadMovies(it) }
    }

    override fun onMovieSelected(movieModel: MovieModel?) {
        var intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra(Constants.MOVIE_OBJ, Gson().toJson(movieModel, MovieModel::class.java))
        startActivity(intent)
    }

    override fun updateMovies(movieListModel: MovieListModel) {
        this.mMovieListingModel = movieListModel
        movieListModel.results?.let { this.mMovieList?.addAll(it) }
        listingAdapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sort -> openSortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openSortDialog() {
        val dialog = Dialog(activity, android.R.style.Theme_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_sort)
        val btnPopular = dialog.findViewById<View>(R.id.btnPopular) as Button
        val btnNowPlaying = dialog.findViewById<View>(R.id.btnNowPlaying) as Button
        val btnUpcoming = dialog.findViewById<View>(R.id.btnUpcoming) as Button
        val btnTopRated = dialog.findViewById<View>(R.id.btnTopRated) as Button
        val imgClose = dialog.findViewById<View>(R.id.imgClose) as ImageView

        imgClose.setOnClickListener{ _ -> dialog.dismiss()}
        btnPopular.setOnClickListener { _ ->
            presenter = popularPresenter
            onPresenterChanged()
            dialog.dismiss()
        }

        btnNowPlaying.setOnClickListener { _ ->
            presenter = nowPlayingPresenter
            onPresenterChanged()
            dialog.dismiss()
        }

        btnUpcoming.setOnClickListener { _ ->
            presenter = upcomingPresenter
            onPresenterChanged()
            dialog.dismiss()
        }
        btnTopRated.setOnClickListener { _ ->
            presenter = topRatedPresenter
            onPresenterChanged()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun onPresenterChanged() {
        presenter?.attachView(this)
        presenter?.initMoviesList()
    }
}
