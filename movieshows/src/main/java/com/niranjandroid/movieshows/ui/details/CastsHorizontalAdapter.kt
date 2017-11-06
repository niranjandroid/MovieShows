package com.niranjandroid.movieshows.ui.details

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
import com.niranjandroid.movieshows.data.model.CastModel
import com.niranjandroid.movieshows.data.model.Crew
import com.niranjandroid.movieshows.data.network.ApiMedia
import kotlinx.android.synthetic.main.item_cast_crew.view.*

/**
 * Created by Niranjan P on 11/6/2017.
 */
class CastsHorizontalAdapter() : RecyclerView.Adapter<CastsHorizontalAdapter.ViewHolder>() {
    var castList: MutableList<CastModel> = ArrayList()
    var crewList: MutableList<Crew> = ArrayList()
    var isCasts: Boolean = false

    fun initWithCasts(castList: MutableList<CastModel>) {
        this.castList = castList
        isCasts = true
    }

    fun initWithCrew(crewList: MutableList<Crew>) {
        this.crewList = crewList
        isCasts = false
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var viewHolderModel = getViewHolderModel(position)
        holder?.itemView?.tvDetails?.text = viewHolderModel.detail
        viewHolderModel.imgPath?.let { loadImage(holder, viewHolderModel.imgPath) }
    }


    private fun loadImage(holder: ViewHolder?, profilePath: String?) {
        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(holder?.itemView?.context)
                .asBitmap()
                .load(ApiMedia.getPosterPath(profilePath))
                .apply(options)
                .into(object : BitmapImageViewTarget(holder?.itemView?.imgProfile) {
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

    private fun getViewHolderModel(position: Int): ViewHolderModel {
        var viewHolderModel = ViewHolderModel()
        if (isCasts) {
            var castModel = castList.get(position)
            viewHolderModel.imgPath = castModel.profilePath
            viewHolderModel.detail = "${castModel.name} as ${castModel.character}"
        } else {
            var crew = crewList.get(position)
            viewHolderModel.imgPath = crew.profilePath
            viewHolderModel.detail = "${crew.name} - ${crew.job}"
        }
        return viewHolderModel
    }

    override fun getItemCount(): Int = if (isCasts) castList.size else crewList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_cast_crew, parent, false)
        var viewHolder = ViewHolder(view);
        return viewHolder;
    }

    data class ViewHolderModel(
            var imgPath: String? = null,
            var detail: String? = null
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}