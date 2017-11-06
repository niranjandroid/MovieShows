package com.niranjandroid.movieshows.data.network

import com.niranjandroid.movieshows.data.model.Video


/**
 * Created by Niranjan P on 10/23/2017.
 */

object ApiMedia {

    val SITE_YOUTUBE = "YouTube"
    val BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342"
    val BASE_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"
    internal val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1\$s"
    internal val YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1\$s/0.jpg"

    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }

    fun getBackdropPath(backdropPath: String?): String {
        return BASE_BACKDROP_PATH + backdropPath
    }

    fun getUrl(video: Video): String =
            if (SITE_YOUTUBE.equals(other = video.site, ignoreCase = true))
                String.format(ApiMedia.YOUTUBE_VIDEO_URL, video.key)
            else ""

    fun getThumbnailUrl(video: Video): String =
            if (SITE_YOUTUBE.equals(other = video.site, ignoreCase = true))
                String.format(ApiMedia.YOUTUBE_THUMBNAIL_URL, video.key)
            else ""
}
