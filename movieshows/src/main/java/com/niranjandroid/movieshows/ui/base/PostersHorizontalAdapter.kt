package com.niranjandroid.movieshows.ui.base

import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.data.network.ApiMedia
import kotlinx.android.synthetic.main.item_poster.view.*

/**
 * Created by Niranjan P on 10/29/2017.
 */

class PostersHorizontalAdapter : RecyclerView.Adapter<PostersHorizontalAdapter.ViewHolder>() {

    var images: MutableList<String>? = null

    fun init(images: MutableList<String>?) {
        this.images = images
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val options = RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)

        Glide.with(holder?.itemView?.context)
                .asBitmap()
                .apply(options)
                .load(ApiMedia.getPosterPath(images?.get(position)))
                .into(object : BitmapImageViewTarget(holder?.itemView?.imgPoster) {
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
    override fun getItemCount(): Int {
        images?.size?.let { return images?.size!! }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_poster, parent, false)
        var viewHolder = ViewHolder(view);
        return viewHolder;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}