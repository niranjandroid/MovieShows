package com.niranjandroid.movieshows.data.network

import com.niranjandroid.movieshows.data.model.ImagesModel
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.model.MovieModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Niranjan P on 08/18/2017.
 */

interface ApiService {
    //MOVIES

    //popular movies
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageNum: String): Flowable<MovieListModel>

    //popular movies
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") pageNum: String): Flowable<MovieListModel>

    //top rated movies
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") pageNum: String): Flowable<MovieListModel>

    // upcoming movies
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") pageNum: String): Flowable<MovieListModel>

    //images
    @GET("movie/{movie_id}/images")
    fun getImages(@Path("movie_id") id: Long?,
                  @Query("include_image_language") query : String): Flowable<ImagesModel>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Long?,
                        @Query("include_image_language") query : String,
                        @Query("append_to_response") query2 : String): Flowable<MovieModel>
}
