package com.niranjandroid.movieshows.data.model

import android.arch.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName


class MovieListModel : ViewModel() {

    @SerializedName("page")
    var page: Long? = null
    @SerializedName("results")
    var results: List<MovieModel>? = ArrayList()
    @SerializedName("total_pages")
    var totalPages: Long? = null
    @SerializedName("total_results")
    var totalResults: Long? = null

    @SerializedName("dates")
    var dates: Dates? = null

    class Dates {

        @SerializedName("maximum")
        var maximum: String? = null
        @SerializedName("minimum")
        var minimum: String? = null

    }
}
