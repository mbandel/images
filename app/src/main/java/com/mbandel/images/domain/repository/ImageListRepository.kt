package com.mbandel.images.domain.repository

import androidx.room.withTransaction
import com.mbandel.images.apiservice.ApiService
import com.mbandel.images.database.ImagesDatabase
import com.mbandel.images.database.dao.ImagesDao
import com.mbandel.images.database.entity.ImageEntity
import com.mbandel.images.domain.Image
import com.mbandel.images.domain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageListRepository @Inject constructor(
    private val imagesDao: ImagesDao,
    private val apiService: ApiService,
    private val database: ImagesDatabase
) {

    fun observeImages(): Flow<List<Image>> {
        return imagesDao.observeImages().map { images ->
            images.map { it.toDomain() }
        }
    }

    suspend fun updateImages(query: String) {
        try {
            val request = apiService.searchImages(query)
            val newImages: List<ImageEntity>? = request.body()?.hits?.map {
                ImageEntity(
                    id = it.id,
                    previewUrl = it.previewURL,
                    detailsUrl = it.webformatURL,
                    previewHeight = it.previewHeight,
                    previewWidth = it.previewWidth,
                    detailsHeight = it.webformatHeight,
                    detailsWidth = it.webformatWidth,
                    user = it.user,
                    tags = it.tags,
                    comments = it.comments,
                    downloads = it.downloads,
                    likes = it.likes
                )
            }
            if (request.isSuccessful) {
                database.withTransaction {
                    imagesDao.deleteImages()
                    imagesDao.addImages(newImages ?: emptyList())
                }
            }
        } catch (e: Exception) {
            println("REQUEST EXCEPTION: " + e.message)
        }
    }

    suspend fun findImageById(id: Int): Image {
        return imagesDao.findImageById(id).toDomain()
    }
}