package com.niranjandroid.movieshows.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Niranjan P on 10/28/2017.
 */

data class Genre(
        @SerializedName("id")
        val id: Long? = null,

        @SerializedName("name")
        var name: String? = null
)