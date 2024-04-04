package com.mbandel.images.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbandel.images.database.DatabaseConstants.IMAGE_ENTITY_TABLE_NAME
import com.mbandel.images.database.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Query("SELECT * FROM $IMAGE_ENTITY_TABLE_NAME")
    fun observeImages(): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<ImageEntity>)

    @Query("DELETE FROM $IMAGE_ENTITY_TABLE_NAME")
    suspend fun deleteImages()
}