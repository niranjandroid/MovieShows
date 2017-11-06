package com.niranjandroid.movieshows.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.niranjandroid.movieshows.data.Config


@Entity(tableName = Config.MOVIE_TABLE_NAME)
data class MovieModel(
        @PrimaryKey
        @SerializedName("id")
        var id: Long?,

        @SerializedName("adult")
        var adult: Boolean? = null,

        @SerializedName("backdrop_path")
        var backdropPath: String? = null,

        @SerializedName("budget")
        var budget: Long? = null,

        @SerializedName("genres")
        var genres: List<Genre>? = null,

        @SerializedName("homepage")
        var homepage: String? = null,

        @SerializedName("imdb_id")
        var imdbId: String? = null,

        @SerializedName("original_language")
        var originalLanguage: String? = null,

        @SerializedName("original_title")
        var originalTitle: String? = null,

        @SerializedName("overview")
        var overview: String? = null,

        @SerializedName("popularity")
        var popularity: Double? = null,

        @SerializedName("poster_path")
        var posterPath: String? = null,

        @SerializedName("production_companies")
        var productionCompanies: MutableList<ProductionCompany>? = null,

        @SerializedName("production_countries")
        var productionCountries: MutableList<ProductionCountry>? = null,

        @SerializedName("release_date")
        var releaseDate: String? = null,

        @SerializedName("revenue")
        var revenue: Long? = null,

        @SerializedName("runtime")
        var runtime: Long? = null,

        @SerializedName("spoken_languages")
        var spokenLanguages: List<SpokenLanguage>? = null,

        @SerializedName("status")
        var status: String? = null,

        @SerializedName("tagline")
        var tagline: String? = null,

        @SerializedName("title")
        var title: String? = null,

        @SerializedName("video")
        var video: Boolean? = null,

        @SerializedName("vote_average")
        var voteAverage: Double? = null,

        @SerializedName("vote_count")
        var voteCount: Long? = null,

        @SerializedName("images")
        var images: ImagesModel,

        @SerializedName("videos")
        var videos: VideoModel,

        @SerializedName("casts")
        var casts: CastsModel
)

data class ProductionCompany(

        @SerializedName("id")
        var id: Long,

        @SerializedName("name")
        var name: String? = null

)

data class ProductionCountry(

        @SerializedName("iso_3166_1")
        var iso31661: String,
        @SerializedName("name")
        var name: String? = null
)

data class SpokenLanguage(
        @SerializedName("iso_639_1")
        var iso6391: String,

        @SerializedName("name")
        var name: String? = null
)

data class VideoModel(
        @SerializedName("results")
        var videos: MutableList<Video>?
)

data class Video(
        @SerializedName("id")
        var id: String,

        @SerializedName("key")
        var key: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("type")
        var type: String?,

        @SerializedName("site")
        var site: String?
)

data class CastsModel(
        @SerializedName("cast")
        var castList: MutableList<CastModel>,

        @SerializedName("crew")
        var crewList: MutableList<Crew>
)

data class CastModel(

        @SerializedName("cast_id")
        var castId: Long,

        @SerializedName("character")
        var character: String,

        @SerializedName("credit_id")
        var creditId: String,

        @SerializedName("gender")
        var gender: Int,

        @SerializedName("id")
        var id: Long? = null,

        @SerializedName("name")
        var name: String,

        @SerializedName("order")
        var order: Long,

        @SerializedName("profile_path")
        var profilePath: String? = null
)

data class Crew(
        @SerializedName("credit_id")
        var creditId: String,

        @SerializedName("department")
        var department: String,

        @SerializedName("gender")
        var gender: Int,

        @SerializedName("id")
        var id: Long,

        @SerializedName("job")
        var job: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("profile_path")
        var profilePath: String? = null
)