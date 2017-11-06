package com.niranjandroid.movieshows.data.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.niranjandroid.movieshows.data.model.*

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
    fun fromDate(Long: List<Long>): String {
        val gson = Gson()
        return gson.toJson(Long)
    }

    @TypeConverter
    fun fromDatesString(value: String): Dates? {
        val listType = object : TypeToken<Dates?>() {}.type
        return Gson().fromJson<Dates>(value, listType)
    }

    @TypeConverter
    fun fromDatesList(dates: Dates?): String {
        val gson = Gson()
        return gson.toJson(dates)
    }

    @TypeConverter
    fun fromGenresString(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson<List<Genre>>(value, listType)
    }

    @TypeConverter
    fun fromGenreList(genreList: List<Genre>): String {
        val gson = Gson()
        return gson.toJson(genreList)
    }

    @TypeConverter
    fun fromProductionCompanyString(value: String): List<ProductionCompany>? {
        val listType = object : TypeToken<List<ProductionCompany>>() {}.type
        return Gson().fromJson<List<ProductionCompany>>(value, listType)
    }

    @TypeConverter
    fun fromProductionCompanyList(productionList: List<ProductionCompany>): String {
        val gson = Gson()
        return gson.toJson(productionList)
    }

    @TypeConverter
    fun fromProductionCountriesString(value: String): List<ProductionCountry>? {
        val listType = object : TypeToken<List<ProductionCountry>>() {}.type
        return Gson().fromJson<List<ProductionCountry>>(value, listType)
    }

    @TypeConverter
    fun fromProductionCountriesList(productionCountryList: List<ProductionCountry>): String {
        val gson = Gson()
        return gson.toJson(productionCountryList)
    }

    @TypeConverter
    fun fromSpokenLanguagesString(value: String): List<SpokenLanguage>? {
        val listType = object : TypeToken<List<SpokenLanguage>>() {}.type
        return Gson().fromJson<List<SpokenLanguage>>(value, listType)
    }

    @TypeConverter
    fun fromSpokentLanguageList(spokenLanguageList: List<SpokenLanguage>): String {
        val gson = Gson()
        return gson.toJson(spokenLanguageList)
    }

    @TypeConverter
    fun fromMovieModelsString(value: String): List<MovieModel>? {
        val listType = object : TypeToken<List<MovieModel>>() {}.type
        return Gson().fromJson<List<MovieModel>>(value, listType)
    }

    @TypeConverter
    fun fromMovieModelsList(movies: List<MovieModel>): String {
        val gson = Gson()
        return gson.toJson(movies)
    }

    @TypeConverter
    fun fromImagesModelString(value: String): ImagesModel? {
        val listType = object : TypeToken<ImagesModel?>() {}.type
        return Gson().fromJson<ImagesModel>(value, listType)
    }

    @TypeConverter
    fun fromImagesModel(imageModel: ImagesModel?): String {
        val gson = Gson()
        return gson.toJson(imageModel)
    }

    @TypeConverter
    fun fromVideoModelString(value: String): VideoModel? {
        val listType = object : TypeToken<VideoModel?>() {}.type
        return Gson().fromJson<VideoModel>(value, listType)
    }

    @TypeConverter
    fun fromVideoModel(videoModel: VideoModel?): String {
        val gson = Gson()
        return gson.toJson(videoModel)
    }

    @TypeConverter
    fun fromCastsModelString(value: String): CastsModel? {
        val listType = object : TypeToken<CastsModel?>() {}.type
        return Gson().fromJson<CastsModel>(value, listType)
    }

    @TypeConverter
    fun fromCastModel(castsModel: CastsModel?): String {
        val gson = Gson()
        return gson.toJson(castsModel)
    }
}