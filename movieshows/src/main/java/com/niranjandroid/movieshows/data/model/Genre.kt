package com.niranjandroid.movieshows.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.niranjandroid.movieshows.data.Config

/**
 * Created by Niranjan P on 10/28/2017.
 */

@Entity(tableName = Config.GENRE_TABLE_NAME)
data class Genre(
        @PrimaryKey
        @SerializedName("id")
        val id: Long,

        @SerializedName("name")
        var name: String? = null
)