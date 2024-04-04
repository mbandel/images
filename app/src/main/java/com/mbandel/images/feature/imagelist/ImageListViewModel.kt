package com.mbandel.images.feature.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbandel.images.domain.usecase.ObserveImageListUseCase
import com.mbandel.images.domain.usecase.UpdateImagesUseCase
import com.mbandel.images.feature.imagelist.state.ImageListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val observeImageListUseCase: ObserveImageListUseCase,
    private val updateImagesUseCase: UpdateImagesUseCase
): ViewModel() {
    private val _state: MutableStateFlow<ImageListState> = MutableStateFlow(ImageListState())
    val state: StateFlow<ImageListState> = _state

    init {
        viewModelScope.launch {
            updateImages(state.value.searchQuery)
        }

        viewModelScope.launch {
            observeImageListUseCase().collect { imageViewDataList ->
                _state.update { it.copy(imageViewDataList = imageViewDataList) }
            }
        }
    }

    fun updateImages(searchQuery: String) {
        _state.update { it.copy(searchQuery = searchQuery) }
        if (state.value.searchQuery != "") {
            viewModelScope.launch {
                updateImagesUseCase(searchQuery)
            }
        }
    }

    fun onDialogClicked(id: Int) {
        _state.update { it.copy(shouldDisplayDialog = true, clickedImageId = id) }
    }

    fun dismissDialog() {
        _state.update { it.copy(shouldDisplayDialog = false) }
    }
}