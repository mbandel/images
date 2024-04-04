package com.mbandel.images.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbandel.images.database.DatabaseConstants

@Entity(tableName = DatabaseConstants.IMAGE_ENTITY_TABLE_NAME)
data class ImageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val previewUrl: String,
    val detailsUrl: String,
    val previewHeight: Int,
    val previewWidth: Int,
    val detailsHeight: Int,
    val detailsWidth: Int,
    val user: String,
    val tags: String,
    val likes: Int,
    val downloads: Int
)