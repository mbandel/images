package com.mbandel.images.domain.usecase

import com.mbandel.images.domain.repository.ImageListRepository
import javax.inject.Inject

class UpdateImagesUseCase @Inject constructor(
    private val imageListRepository: ImageListRepository
) : suspend (String) -> Unit {
    override suspend fun invoke(query: String) {
        imageListRepository.updateImages(query)
    }
}