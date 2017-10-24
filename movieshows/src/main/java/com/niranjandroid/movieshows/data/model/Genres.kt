package com.niranjandroid.movieshows.data.model

import android.arch.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName

class Genres : ViewModel() {

    @SerializedName("genres")
    var genres: List<Genres>? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null

}
