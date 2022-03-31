package com.example.flickagram.domain.sources

import com.example.flickagram.data.database.dao.PhotosDao
import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.domain.model.Photo
import com.example.flickagram.domain.model.PhotosBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainSource @Inject constructor(
    private val photosAPI: PhotosAPI, 
    private val photosDao: PhotosDao
) {

    fun getImages(page: Int): Response<PhotosBody> {
        return photosAPI.getImages(page = page).execute()
    }

    suspend fun storeImages(photos: List<Photo>) {
        photos.forEach {
            photosDao.insertPhoto(it)
        }
    }
    fun getImages(): StateFlow<List<Photo>> {
           return photosDao.getPhotosList() as StateFlow<List<Photo>>
    }
}