package com.mbandel.images.remote

data class ImageRemote(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)