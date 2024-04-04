package com.mbandel.images

import com.mbandel.images.domain.repository.ImageListRepository
import com.mbandel.images.domain.usecase.FindImageByIdUseCase
import com.mbandel.images.feature.imagedetails.ImageDetailsViewModel
import com.mbandel.images.feature.imagedetails.state.ImageDetailsState
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class ImageDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val imageListRepository = mockk<ImageListRepository> {
        coEvery { findImageById(any()) } returns testImage()
    }

    private val viewModel: ImageDetailsViewModel by lazy {
        ImageDetailsViewModel(FindImageByIdUseCase(imageListRepository))
    }

    @Test
    fun `when findImageById update state`() {
        viewModel.findImageById(1)
        viewModel.state.value shouldBe(ImageDetailsState(testImageDetailViewData()))
    }

    @Test
    fun `when findImageById useCase is called `() {
        viewModel.findImageById(1)
        coVerify { imageListRepository.findImageById(1) }
    }

}