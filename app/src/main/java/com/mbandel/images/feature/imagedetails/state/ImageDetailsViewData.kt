package com.mbandel.images.feature.imagedetails.state

import com.mbandel.images.domain.Image

data class ImageDetailsViewData(
    val user: String,
    val tags: String,
    val url: String,
    val width: Int,
    val height: Int,
    val downloads: String,
    val comments: String,
    val likes: String
)

fun Image.toImageDetailsViewData(): ImageDetailsViewData =
    ImageDetailsViewData(
        user = this.user,
        tags = this.tags,
        url = this.detailsUrl,
        width = this.detailsWidth,
        height = this.detailsHeight,
        downloads = this.downloads.toString(),
        comments = this.comments.toString(),
        likes = this.likes.toString()
    )
