package com.mbandel.images.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbandel.images.database.dao.ImagesDao
import com.mbandel.images.database.entity.ImageEntity

@Database(
    entities = [ImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun getImagesDao(): ImagesDao
}