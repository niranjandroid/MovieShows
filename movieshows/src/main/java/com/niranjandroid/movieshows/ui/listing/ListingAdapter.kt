package com.niranjandroid.movieshows.ui.listing

import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.network.ApiMedia


/**
 * Created by Niranjan P on 10/21/2017.
 */
class ListingAdapter(var movieList: MutableList<MovieModel>?, var itemClickListener: ItemClickListener?)
    : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        var viewHolder = ViewHolder(view);
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (position == movieList?.size) {
            initShowMore(holder)
        }
        else {
            var movie: MovieModel? = movieList?.get(position)
            updateUI(holder, movie)
        }
    }

    private fun initShowMore(holder: ViewHolder?) {
        holder?.imgMore?.visibility = View.VISIBLE
        holder?.imgMore?.setOnClickListener({v -> itemClickListener?.onMoreBtnClicked()})
    }

    private fun updateUI(holder: ViewHolder?, movie: MovieModel?) {
        loadImage(holder, movie?.posterPath)
        holder?.imgMore?.visibility = View.GONE
        holder?.tvTitle?.text = movie?.title
        holder?.itemView?.setOnClickListener { v -> itemClickListener?.onMovieSelected(movie) }
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
                .into(object : BitmapImageViewTarget(holder?.imgMovie) {
                    override fun onResourceReady(bitmap: Bitmap?, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate({ palette -> setBackgroundColor(palette, holder) })
                    }
                })
    }

    private fun setBackgroundColor(palette: Palette, holder: ViewHolder?) {
        holder?.itemView?.context
                ?.resources?.getColor(R.color.black_translucent_60)
                ?.let { palette.getVibrantColor(it) }?.let { holder?.titleBackground?.setBackgroundColor(it) }
    }

    override fun getItemCount(): Int = if (movieList?.size!! > 0) movieList?.size!! + 1 else 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgMovie: ImageView? = null
        var tvTitle: TextView? = null
        var titleBackground: View? = null
        var imgMore: ImageView ?= null

        init {
            imgMovie = itemView?.findViewById<View>(R.id.img_movie_poster) as ImageView
            tvTitle = itemView?.findViewById<View>(R.id.tv_movie_name) as TextView
            titleBackground = itemView?.findViewById<View>(R.id.title_background) as View
            imgMore = itemView?.findViewById<View>(R.id.img_more) as ImageView
        }
    }
}