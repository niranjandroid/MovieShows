package com.niranjandroid.movieshows.data.network

import com.niranjandroid.movieshows.data.model.MovieListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Niranjan P on 08/18/2017.
 */

interface ApiService {
    //MOVIES

    //popular movies
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageNum: String): Observable<MovieListModel>

    //popular movies
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") pageNum: String): Observable<MovieListModel>

    //top rated movies
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") pageNum: String): Observable<MovieListModel>

    // upcoming movies
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") pageNum : String) : Observable<MovieListModel>

}
