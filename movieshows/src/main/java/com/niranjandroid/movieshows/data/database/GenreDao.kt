package com.niranjandroid.movieshows.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.niranjandroid.movieshows.data.Config
import com.niranjandroid.movieshows.data.model.Genre
import io.reactivex.Flowable

/**
 * Created by Niranjan P on 10/25/2017.
 */

@Dao
interface GenreDao {
    @Query("SELECT * FROM " + Config.GENRE_TABLE_NAME)
    fun getAllGenres(): Flowable<List<Genre>>

    @Query("SELECT * FROM " + Config.GENRE_TABLE_NAME + " WHERE id == :id")
    fun getGenreById(id : String) : Flowable<Genre>

    @Insert
    fun insert(genre: List<Genre>)

    @Query("DELETE FROM " + Config.GENRE_TABLE_NAME)
    fun deleteAll()
}