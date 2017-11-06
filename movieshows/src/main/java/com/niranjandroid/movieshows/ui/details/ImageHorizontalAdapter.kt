package com.niranjandroid.movieshows.ui.details

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
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
import com.niranjandroid.movieshows.data.model.Video
import com.niranjandroid.movieshows.data.network.ApiMedia
import kotlinx.android.synthetic.main.item_cast_crew.view.*

/**
 * Created by Niranjan P on 11/6/2017.
 */
class ImageHorizontalAdapter() : RecyclerView.Adapter<ImageHorizontalAdapter.ViewHolder>() {
    var castList: MutableList<CastModel> = ArrayList()
    var crewList: MutableList<Crew> = ArrayList()
    var trailers: MutableList<Video> = ArrayList()
    val CAST = 0
    val CREW = 1
    val TRAILERS = 2
    var type: Int = CAST

    fun initWithCasts(castList: MutableList<CastModel>) {
        this.castList = castList
        type = CAST
    }

    fun initWithCrew(crewList: MutableList<Crew>) {
        this.crewList = crewList
        type = CREW
    }

    fun initWithTrailers(videos: MutableList<Video>) {
        this.trailers = videos
        type = TRAILERS
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var viewHolderModel = getViewHolderModel(position)
        holder?.itemView?.tvDetails?.text = viewHolderModel.detail
        viewHolderModel.imgPath?.let { loadImage(holder, viewHolderModel.imgPath) }
        if(type == TRAILERS) {
            val params = RecyclerView.LayoutParams(380, 300)
            params.rightMargin = 6
            holder?.itemView?.layoutParams = params
            holder?.itemView?.setOnClickListener { _ -> onThumbnailClickListener(trailers.get(position), holder) }
        }
    }

    private fun onThumbnailClickListener(video: Video, holder:ViewHolder?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ApiMedia.getUrl(video)))
        holder?.itemView?.context?.startActivity(intent)
    }


    private fun loadImage(holder: ViewHolder?, imgPath: String?) {
        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(holder?.itemView?.context)
                .asBitmap()
                .load(imgPath)
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
        when (type) {
            CAST -> {
                var castModel = castList.get(position)
                castModel?.profilePath?.let { viewHolderModel.imgPath = ApiMedia.getPosterPath(castModel.profilePath) }
                viewHolderModel.detail = "${castModel.name} as ${castModel.character}"
            }
            CREW -> {
                var crew = crewList.get(position)
                crew.profilePath?.let { viewHolderModel.imgPath = ApiMedia.getPosterPath(crew.profilePath) }
                viewHolderModel.detail = "${crew.name} - ${crew.job}"
            }
            TRAILERS -> {
                var video = trailers.get(position)
                viewHolderModel.imgPath = ApiMedia.getThumbnailUrl(video)
                viewHolderModel.detail = video?.name
            }
        }
        return viewHolderModel
    }

    override fun getItemCount(): Int = when (type) {
        CAST -> castList.size
        CREW -> crewList.size
        TRAILERS -> trailers.size
        else -> 0
    }

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