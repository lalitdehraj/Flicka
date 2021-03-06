package com.example.flickagram.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PhotosTable")
data class Photo(
    @PrimaryKey
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("height_h")
    val height: Int,
    @SerializedName("width_h")
    val width: Int,
    @SerializedName("url_h")
    val url: String?
)

data class Photos(
    @SerializedName("photo")
    var photoList: List<Photo>,
    @SerializedName("page")
    val currentPage: Int,
    @SerializedName("pages")
    val totalPagesCount: Int
)

data class PhotosBody(
    @SerializedName("photos")
    val photos: Photos
)