package com.niranjandroid.movieshows.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.niranjandroid.movieshows.data.Config
import com.niranjandroid.movieshows.data.model.MovieListModel
import io.reactivex.Flowable

/**
 * Created by Niranjan P on 10/25/2017.
 */
@Dao
interface MovieListDao {
    @Query("SELECT * FROM " + Config.MOVIE_LIST_TABLE_NAME)
    fun getAllMovieListModels(): Flowable<List<MovieListModel>>

    @Query("SELECT * FROM " + Config.MOVIE_LIST_TABLE_NAME + " WHERE page == :page")
    fun getMovieListModelByPage(page : String) : Flowable<MovieListModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieListModel: MovieListModel)

    @Query("DELETE FROM " + Config.MOVIE_LIST_TABLE_NAME)
    fun deleteAll()
}