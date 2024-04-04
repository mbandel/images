package com.mbandel.images.domain

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
    val likes: Int,
    val downloads: Int
)