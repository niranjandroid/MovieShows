package com.niranjandroid.movieshows.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.model.MovieModel

/**
 * Created by Niranjan P on 10/25/2017.
 */


@Database(entities = arrayOf(MovieListModel::class, MovieModel::class),
        version = 1, exportSchema = false)
@TypeConverters(DBModelConverter::class)
abstract class MovieShowsDB : RoomDatabase() {
    abstract fun movieListDao(): MovieListDao
    abstract fun movieDao(): MovieDao
}
