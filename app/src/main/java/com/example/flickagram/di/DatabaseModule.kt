package com.example.flickagram.di

import android.content.Context
import androidx.room.Room
import com.example.flickagram.BuildConfig
import com.example.flickagram.data.database.AppDatabase
import com.example.flickagram.data.database.dao.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase (@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.APP_DATABASE
        ).build()
    }

}