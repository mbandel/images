package com.mbandel.images.feature.imagelist.state

data class ImageViewData(
    val id: Int,
    val user: String,
    val tags: String,
    val url: String,
    val width: Int,
    val height: Int
)
