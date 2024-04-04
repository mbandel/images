package com.mbandel.images.feature.imagelist.state

data class ImageListState(
    val searchQuery: String = "",
    val imageViewDataList: List<ImageViewData> = emptyList()
)
