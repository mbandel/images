package com.mbandel.images.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun DisplayImage(imageUrl: String, width: Int, height: Int) {
    val request = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .build()

    val painter = rememberAsyncImagePainter(request)

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(width = width.dp, height = height.dp)
    )
}