package com.example.flickagram.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flickagram.data.database.dao.PhotosDao
import com.example.flickagram.domain.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): PhotosDao


}