package com.niranjandroid.movieshows.data.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.niranjandroid.movieshows.data.model.Dates
import com.niranjandroid.movieshows.data.model.MovieModel

/**
 * Created by Niranjan P on 10/28/2017.
 */
class DBModelConverter {
    @TypeConverter
    fun fromString(value: String): List<Long>? {
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson<List<Long>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(Long: List<Long>): String {
        val gson = Gson()
        return gson.toJson(Long)
    }

    @TypeConverter
    fun fromDatesString(value: String): Dates? {
        val listType = object : TypeToken<Dates>() {}.type
        return Gson().fromJson<Dates>(value, listType)
    }

    @TypeConverter
    fun fromDatesList(dates: Dates): String {
        val gson = Gson()
        return gson.toJson(dates)
    }

    @TypeConverter
    fun fromMovieModelsString(value: String): List<MovieModel>? {
        val listType = object : TypeToken<List<MovieModel>>() {}.type
        return Gson().fromJson<List<MovieModel>>(value, listType)
    }

    @TypeConverter
    fun fromMovieModelsList(dates: List<MovieModel>): String {
        val gson = Gson()
        return gson.toJson(dates)
    }
}