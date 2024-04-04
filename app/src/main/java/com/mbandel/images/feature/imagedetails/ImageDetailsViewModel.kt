package com.mbandel.images.feature.imagedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbandel.images.domain.usecase.FindImageByIdUseCase
import com.mbandel.images.feature.imagedetails.state.ImageDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    private val findImageByIdUseCase: FindImageByIdUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<ImageDetailsState> = MutableStateFlow(ImageDetailsState(null))
    val state: StateFlow<ImageDetailsState> = _state

    fun findImageById(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(imageDetailsViewData = findImageByIdUseCase(id)) }
        }
    }


}