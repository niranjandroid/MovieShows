package com.niranjandroid.movieshows.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.niranjandroid.movieshows.data.Config
import com.niranjandroid.movieshows.data.model.MovieModel
import io.reactivex.Flowable

/**
 * Created by Niranjan P on 10/25/2017.
 */

@Dao
interface MovieDao {
    @Query("SELECT * FROM " + Config.MOVIE_TABLE_NAME)
    fun getAllMovieModels(): Flowable<List<MovieModel>>

    @Query("SELECT * FROM " + Config.MOVIE_TABLE_NAME + " WHERE id == :id")
    fun getMovieById(id : String) : Flowable<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieModel: MovieModel)

    @Query("DELETE FROM " + Config.MOVIE_TABLE_NAME)
    fun deleteAll()
}