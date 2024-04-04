package com.mbandel.images.feature.imagelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mbandel.images.R
import com.mbandel.images.feature.imagedetails.ImageDetailsScreen
import com.mbandel.images.feature.imagelist.state.ImagePreviewViewData
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
    val focusManager = LocalFocusManager.current
    val navigator = LocalNavigator.currentOrThrow

    if (state.shouldDisplayDialog) {
        DisplayDialog(
            onConfirmClicked = {
                viewModel.dismissDialog()
                navigator.push(ImageDetailsScreen(state.clickedImageId))
            },
            onDismissClicked = { viewModel.dismissDialog() }
        )
    }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.updateImages(it) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions { focusManager.clearFocus() },
            label = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.imageViewDataList) { image ->
                ImageItem(
                    imageViewData = image,
                    onImageClicked = { viewModel.onDialogClicked(image.id) })
            }
        }
    }

}

@Composable
private fun ImageItem(imageViewData: ImagePreviewViewData, onImageClicked: (Int) -> Unit) {
    Column(modifier = Modifier.clickable {
        onImageClicked(imageViewData.id)
    }) {
        Row {
            DisplayImage(
                imageUrl = imageViewData.url,
                width = imageViewData.width,
                height = imageViewData.height
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = stringResource(id = R.string.user) + imageViewData.user)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = stringResource(id = R.string.tags) + imageViewData.tags)
            }
        }
    }
}

@Composable
private fun DisplayDialog(onConfirmClicked: () -> Unit, onDismissClicked: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.display_details))
        },
        text = {
            Text(text = stringResource(id = R.string.display_details_question))
        },
        onDismissRequest = { onDismissClicked() },
        confirmButton = {
            TextButton(
                onClick = { onConfirmClicked() }
            ) {
                Text(stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissClicked() }
            ) {
                Text(stringResource(id = R.string.dismiss))
            }
        }
    )
}