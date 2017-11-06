package com.niranjandroid.movieshows.data.model

import com.google.gson.annotations.SerializedName
data class ImagesModel (

    @SerializedName("backdrops")
    var backdrops: MutableList<ImageModel>? = null,

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("posters")
    var posters: MutableList<ImageModel>? = null

)

data class ImageModel(

        @SerializedName("file_path")
        var mFilePath: String? = null
)