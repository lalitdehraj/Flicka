package com.example.flickagram.di

import com.example.flickagram.BuildConfig
import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.data.network.config.APIService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    fun provideAPIService(retrofit: Retrofit) : APIService {
        return APIService(retrofit = retrofit)
    }

    @Provides
    fun providePhotosAPI(apiService: APIService) : PhotosAPI {
        return apiService.createAPI(PhotosAPI::class.java)
    }
}