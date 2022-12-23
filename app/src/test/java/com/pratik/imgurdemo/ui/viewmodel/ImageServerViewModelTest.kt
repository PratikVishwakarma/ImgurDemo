package com.pratik.imgurdemo.ui.viewmodel

import androidx.test.annotation.UiThreadTest
import com.android.example.livedatabuilder.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import com.pratik.imgurdemo.model.networkAPI.useCase.TestAPIUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ImageServerViewModelTest {

    @Mock
    private lateinit var apiUseCase: TestAPIUseCase
    private lateinit var imageServerViewModel: ImageServerViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        imageServerViewModel = ImageServerViewModel(apiUseCase)
    }

    @Test
    @UiThreadTest
    fun `call the api with search text, returns true`() {
        imageServerViewModel.setSearchText("cat")
        imageServerViewModel.getTopImageOfWeek()
        val value = imageServerViewModel.imageList.getOrAwaitValue()
        assertThat(value.size).isNotEqualTo(0)
    }

    @Test
    @UiThreadTest
    fun `call the api with not result, returns true`() {
        imageServerViewModel.setSearchText("")
        imageServerViewModel.getTopImageOfWeek()
        val value = imageServerViewModel.imageList.getOrAwaitValue()
        assertThat(value.size).isEqualTo(0)
    }


}