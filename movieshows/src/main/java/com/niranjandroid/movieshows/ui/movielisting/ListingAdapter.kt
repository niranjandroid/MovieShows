package com.niranjandroid.movieshows.ui.movielisting

import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.network.ApiMedia
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Niranjan P on 10/21/2017.
 */
class ListingAdapter : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {
    var movieList: MutableList<MovieModel>? = null
    var itemClickListener: ItemClickListener? = null
    fun init(movieList: MutableList<MovieModel>?, itemClickListener: ItemClickListener?) {
        this.movieList = movieList
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        var viewHolder = ViewHolder(view);
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (position == movieList?.size) {
            initShowMore(holder)
        } else {
            var movie: MovieModel? = movieList?.get(position)
            updateUI(holder, movie)
        }
    }

    private fun initShowMore(holder: ViewHolder?) {
        holder?.itemView?.imgMore?.visibility = View.VISIBLE
        holder?.itemView?.imgMore?.setOnClickListener({ _ -> itemClickListener?.onMoreBtnClicked() })
    }

    private fun updateUI(holder: ViewHolder?, movie: MovieModel?) {
        loadImage(holder, movie?.posterPath)
        holder?.itemView?.imgMore?.visibility = View.GONE
        holder?.itemView?.tvTitle?.text = movie?.title
        holder?.itemView?.setOnClickListener { _ -> itemClickListener?.onMovieSelected(movie) }
    }

    private fun loadImage(holder: ViewHolder?, posterPath: String?) {
        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(holder?.itemView?.context)
                .asBitmap()
                .load(ApiMedia.getPosterPath(posterPath))
                .apply(options)
                .into(object : BitmapImageViewTarget(holder?.itemView?.imgMovie) {
                    override fun onResourceReady(bitmap: Bitmap?, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate({ palette -> setBackgroundColor(palette, holder) })
                    }
                })
    }

    private fun setBackgroundColor(palette: Palette, holder: ViewHolder?) {
        ContextCompat.getColor(holder?.itemView?.context, R.color.black_translucent_60)
                .let { palette.getVibrantColor(it) }.let { holder?.itemView?.titleBackground?.setBackgroundColor(it) }
    }

    override fun getItemCount(): Int = if (movieList?.size!! > 0) movieList?.size!! + 1 else 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}