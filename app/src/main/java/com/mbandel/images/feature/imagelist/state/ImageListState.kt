package com.mbandel.images.feature.imagelist.state

data class ImageListState(
    val searchQuery: String = "fruits",
    val shouldDisplayDialog: Boolean = false,
    val imageViewDataList: List<ImagePreviewViewData> = emptyList(),
    val clickedImageId: Int = 0
)
