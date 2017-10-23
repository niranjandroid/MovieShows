package com.niranjandroid.movieshows.ui.movielisting

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.model.MovieModel

/**
 * Created by Niranjan P on 10/21/2017.
 */
class MovieListingAdapter(var movieListModel : MovieListModel?) : RecyclerView.Adapter<MovieListingAdapter.ViewHolder>() {

    var movieList : List<MovieModel> ?= ArrayList<MovieModel>()

    fun updateMovies(movieListModel: MovieListModel?) {
        this.movieListModel = movieListModel;
        this.movieList = movieListModel?.results
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        var viewHolder = ViewHolder(view);
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int= movieList?.size!!

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }
    }
}