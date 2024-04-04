package com.mbandel.images.domain

import com.mbandel.images.database.entity.ImageEntity

data class Image(
    val id: Int,
    val previewUrl: String,
    val detailsUrl: String,
    val previewHeight: Int,
    val previewWidth: Int,
    val detailsHeight: Int,
    val detailsWidth: Int,
    val user: String,
    val tags: String,
    val comments: Int,
    val likes: Int,
    val downloads: Int
)

fun ImageEntity.toDomain(): Image =
        Image(
            id = this.id,
            previewUrl = this.previewUrl,
            detailsUrl = this.detailsUrl,
            previewHeight = this.previewHeight,
            previewWidth = this.previewWidth,
            detailsHeight = this.detailsHeight,
            detailsWidth = this.detailsWidth,
            user = this.user,
            comments = this.comments,
            downloads = this.downloads,
            tags = this.tags,
            likes = this.likes
        )

