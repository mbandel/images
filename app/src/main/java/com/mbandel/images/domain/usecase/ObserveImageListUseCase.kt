package com.mbandel.images.domain.usecase

import com.mbandel.images.domain.repository.ImageListRepository
import com.mbandel.images.feature.imagelist.state.ImagePreviewViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveImageListUseCase @Inject constructor(
    private val imageListRepository: ImageListRepository
) : () -> Flow<List<ImagePreviewViewData>> {
    override fun invoke(): Flow<List<ImagePreviewViewData>> {
        return imageListRepository.observeImages().map { images ->
            images.map {
                ImagePreviewViewData(
                    id = it.id,
                    user = it.user,
                    tags = it.tags,
                    url = it.previewUrl,
                    height = it.previewHeight,
                    width = it.previewWidth
                )
            }
        }
    }
}