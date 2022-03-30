package com.example.flickagram.domain.sources

import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.domain.model.PhotosBody
import retrofit2.Response
import javax.inject.Inject

class MainSource @Inject constructor(private val photosAPI: PhotosAPI) {

    fun getImages(page: Int): Response<PhotosBody> {
        return photosAPI.getImages(page = page).execute()

    }
}