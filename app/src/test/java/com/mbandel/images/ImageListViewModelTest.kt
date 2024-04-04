package com.mbandel.images

import com.mbandel.images.domain.repository.ImageListRepository
import com.mbandel.images.domain.usecase.ObserveImageListUseCase
import com.mbandel.images.domain.usecase.UpdateImagesUseCase
import com.mbandel.images.feature.imagelist.ImageListViewModel
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class ImageListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val imageListRepository = mockk<ImageListRepository> {
        every { observeImages() } returns flowOf(listOf(testImage()))
        coEvery { updateImages(any()) } returns Unit
    }

    private val updateImagesUseCase = UpdateImagesUseCase(imageListRepository)
    private val observeImageListUseCase = ObserveImageListUseCase(imageListRepository)

    private val viewModel by lazy {
        ImageListViewModel(
            observeImageListUseCase = observeImageListUseCase,
            updateImagesUseCase =  updateImagesUseCase
        )
    }

    @Test
    fun `when update search query state is updated`() {
        viewModel.updateImages("test query")
        viewModel.state.value shouldBe viewModel.state.value.copy(searchQuery = "test query")
    }

    @Test
    fun `when onDialogClicked state is updated`() {
        viewModel.onDialogClicked(2)
        viewModel.state.value shouldBe viewModel.state.value.copy(
            shouldDisplayDialog = true,
            clickedImageId = 2
        )
    }

    @Test
    fun `when dialog dismissed shouldDisplayDialog is false`() {
        viewModel.dismissDialog()
        viewModel.state.value shouldBe viewModel.state.value.copy(shouldDisplayDialog = false)
    }

    @Test
    fun `when updateImages state is updated`() {
        viewModel.updateImages("not empty")
        viewModel.state.value shouldBe viewModel.state.value.copy(searchQuery = "not empty")
    }

    @Test
    fun `when updateImages then updateImages is called `() {
        viewModel.updateImages("not empty")
        coVerify(exactly = 1) { imageListRepository.updateImages("not empty") }
    }

}