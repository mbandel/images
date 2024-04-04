package com.mbandel.images.feature.imagelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.mbandel.images.feature.imagelist.state.ImageViewData
import com.mbandel.images.ui.theme.DisplayImage

object ImageListScreen : Screen {
    @Composable
    override fun Content() {
        ImageListComposableScreen(viewModel = getViewModel())
    }
}

@Composable
private fun ImageListComposableScreen(viewModel: ImageListViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.updateImages(it) },
            label = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.imageViewDataList) { image ->
                ImageItem(imageViewData = image)
            }
        }
    }

}

@Composable
private fun ImageItem(imageViewData: ImageViewData) {
    Column {
        DisplayImage(
            imageUrl = imageViewData.url,
            width = imageViewData.width,
            height = imageViewData.height
        )
        Text(text = "user : ${imageViewData.user}")
        Text(text = "tags: ${imageViewData.tags}")
    }
}