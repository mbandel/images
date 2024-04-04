package com.mbandel.images.domain.repository

import androidx.room.withTransaction
import com.mbandel.images.apiservice.ApiConstants
import com.mbandel.images.apiservice.ApiService
import com.mbandel.images.database.ImagesDatabase
import com.mbandel.images.database.dao.ImagesDao
import com.mbandel.images.database.entity.ImageEntity
import com.mbandel.images.domain.Image
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
            images.map {
                Image(
                    id = it.id,
                    previewUrl = it.previewUrl,
                    detailsUrl = it.detailsUrl,
                    previewHeight = it.previewHeight,
                    previewWidth = it.previewWidth,
                    detailsHeight = it.detailsHeight,
                    detailsWidth = it.detailsWidth,
                    user = it.user,
                    likes = it.likes,
                    downloads = it.downloads,
                    tags = it.tags
                )
            }
        }
    }

    suspend fun updateImages(query: String) {
        try {
            val request = apiService.searchImages(query)
            println("REQUEST SUCCESS: ${request.isSuccessful}")
            println("REQUEST REASON: ${request.code()}")

            val newImages: List<ImageEntity>? = request.body()?.hits?.map {
                ImageEntity(
                    id = it.id,
                    previewUrl = it.previewURL,
                    detailsUrl = it.largeImageURL,
                    previewHeight = it.previewHeight,
                    previewWidth = it.previewWidth,
                    detailsHeight = it.imageHeight,
                    detailsWidth = it.imageWidth,
                    user = it.user,
                    tags = it.tags,
                    likes = it.likes,
                    downloads = it.downloads
                )
            }
            println("REQUEST LIST: ${request.body()?.hits}")
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
}