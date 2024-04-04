package com.mbandel.images.domain.usecase

import com.mbandel.images.domain.repository.ImageListRepository
import com.mbandel.images.feature.imagedetails.state.ImageDetailsViewData
import com.mbandel.images.feature.imagedetails.state.toImageDetailsViewData
import javax.inject.Inject

class FindImageByIdUseCase @Inject constructor(
    private val imageListRepository: ImageListRepository
) : suspend (Int) -> ImageDetailsViewData {
    override suspend fun invoke(id: Int): ImageDetailsViewData {
        return imageListRepository.findImageById(id).toImageDetailsViewData()
    }
}