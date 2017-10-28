package com.niranjandroid.movieshows.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.niranjandroid.movieshows.data.Config

@Entity(tableName = Config.MOVIE_LIST_TABLE_NAME)
data class MovieListModel(

        @PrimaryKey
        @SerializedName("page")
        val page: Long,

        @SerializedName("results")
        var results: List<MovieModel>? = ArrayList(),

        @SerializedName("total_pages")
        @ColumnInfo(name = "total_pages")
        var totalPages: Long? = null,

        @SerializedName("total_results")
        @ColumnInfo(name = "total_results")
        var totalResults: Long? = null,

        @SerializedName("dates")
        var dates: Dates? = null
)

data class Dates(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @SerializedName("maximum")
        var maximum: String? = null,

        @SerializedName("minimum")
        var minimum: String? = null

)