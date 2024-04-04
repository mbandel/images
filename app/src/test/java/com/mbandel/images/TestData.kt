package com.mbandel.images

import com.mbandel.images.domain.Image
import com.mbandel.images.feature.imagedetails.state.ImageDetailsViewData

fun testImageDetailViewData() = ImageDetailsViewData(
    user = "user",
    tags = "fruit, banana, yellow",
    url = "details",
    width = 20,
    height = 40,
    downloads = "12",
    comments = "2",
    likes = "30"
)

fun testImage() = Image(
    id = 1,
    previewUrl = "preview",
    detailsUrl = "details",
    previewHeight = 20,
    previewWidth = 10,
    detailsHeight = 40,
    detailsWidth = 20,
    user = "user",
    comments = 2,
    downloads = 12,
    tags = "fruit, banana, yellow",
    likes = 30
)
