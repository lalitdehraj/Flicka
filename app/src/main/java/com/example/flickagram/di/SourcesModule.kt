package com.example.flickagram.di

import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.domain.sources.MainSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SourcesModule {

    @Provides
    fun provideMainSource(photosAPI: PhotosAPI): MainSource {
        return MainSource(photosAPI)
    }

}