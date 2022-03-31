package com.example.flickagram.domain.sources

import com.example.flickagram.data.database.AppDatabase
import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.domain.model.Photo
import com.example.flickagram.domain.model.PhotosBody
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainSource @Inject constructor(
    private val photosAPI: PhotosAPI,
    private val appDatabase: AppDatabase
) {

    fun getImages(page: Int): Response<PhotosBody> {
        return photosAPI.getImages(page = page).execute()
    }

    suspend fun storeImage(photo: Photo) {
        appDatabase.getPhotoDao().insertPhoto(photo)
    }

    fun getImages(): Flow<List<Photo>> {
        return appDatabase.getPhotoDao().getPhotosList()
    }
}