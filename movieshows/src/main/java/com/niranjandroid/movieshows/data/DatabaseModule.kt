package com.niranjandroid.movieshows.data

import android.arch.persistence.room.Room
import android.content.Context
import com.niranjandroid.movieshows.data.database.MovieDao
import com.niranjandroid.movieshows.data.database.MovieListDao
import com.niranjandroid.movieshows.data.database.MovieShowsDB
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Niranjan P on 10/25/2017.
 */


@Module
class DatabaseModule {
    companion object {
        const val DATABASE = "database_name"
    }

    @Provides
    @Singleton
    @Named(DATABASE)
    fun provideDatabaseName() : String{
        return Config.DATABASE_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context, @Named(DATABASE) databaseName : String ) : MovieShowsDB{
        return Room.databaseBuilder(context, MovieShowsDB::class.java, databaseName).build()
    }

    @Provides
    @Singleton
    fun provideMovieListDao(movieShowsDB: MovieShowsDB) : MovieListDao{
        return movieShowsDB.movieListDao()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieShowsDB: MovieShowsDB) : MovieDao {
        return movieShowsDB.movieDao()
    }


}