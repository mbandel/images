package com.mbandel.images.feature.imagedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.mbandel.images.R
import com.mbandel.images.ui.theme.DisplayImage

data class ImageDetailsScreen(val id: Int) : Screen {
    @Composable
    override fun Content() {
        ImageDetailsComposableScreen(id = id, viewModel = getViewModel())
    }
}

@Composable
private fun ImageDetailsComposableScreen(id: Int, viewModel: ImageDetailsViewModel) {
    LaunchedEffect(true) {
        viewModel.findImageById(id)
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value
    val viewData = state.imageDetailsViewData
    if (viewData != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(text = stringResource(id = R.string.user) + " " + viewData.user)
            Text(text =  stringResource(id = R.string.user) + " " +  viewData.tags)
            Text(text =  stringResource(id = R.string.likes) + " " + viewData.likes)
            Text(text =  stringResource(id = R.string.comments) + " " + viewData.comments)
            Text(text =  stringResource(id = R.string.downloads) + " " + viewData.downloads)
            DisplayImage(imageUrl = viewData.url, width = viewData.width, height = viewData.width)
        }
    }
}