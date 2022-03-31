package com.example.flickagram.di

import com.example.flickagram.data.database.AppDatabase
import com.example.flickagram.data.database.dao.PhotosDao
import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.domain.sources.MainSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(ActivityComponent::class)
@Module
object SourcesModule {

    @Provides
    fun provideMainSource(photosAPI: PhotosAPI, appDatabase: AppDatabase): MainSource {
        return MainSource(photosAPI, appDatabase)
    }

}