package com.example.flickagram.data.network.api

import com.example.flickagram.BuildConfig
import com.example.flickagram.domain.model.Photos
import com.example.flickagram.domain.model.PhotosBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface PhotosAPI {
    @GET("rest/")
    fun getImages(
        @Query("method") method: String = "flickr.interestingness.getList",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format: String = "json",
        @Query("per_page") count: Int = 20,
        @Query("extras") extras: String = "date_taken,url_h",
        @Query("page") page: Int = 1,
        @Query("nojsoncallback") no: Int = 1,
    ) : Call<PhotosBody>
}
