package com.niranjandroid.movieshows.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.niranjandroid.movieshows.data.Config


@Entity(tableName = Config.MOVIE_TABLE_NAME)
data class MovieModel (

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @ColumnInfo(name ="backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("genre_ids")
    @ColumnInfo(name ="genre_ids")
    var genreIds: List<Long>? = null,

    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("original_language")
    @ColumnInfo(name ="original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @ColumnInfo(name ="original_title")
    var originalTitle: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("poster_path")
    @ColumnInfo(name ="poster_path")
    var posterPath: String? = null,

    @SerializedName("release_date")
    @ColumnInfo(name ="release_date")
    var releaseDate: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("vote_average")
    @ColumnInfo(name ="vote_average")
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    @ColumnInfo(name ="vote_count")
    var voteCount: Long? = null

)
