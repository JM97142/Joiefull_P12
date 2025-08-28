package com.example.joiefull_p12

import com.example.joiefull_p12.data.api.ClothesApiService
import com.example.joiefull_p12.data.models.ClothesItem
import com.example.joiefull_p12.data.models.Picture
import com.example.joiefull_p12.ui.viewmodels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var apiService: ClothesApiService

    @Before
    fun setup() {
        apiService = mock() // Mockito-Kotlin
    }

    @Test
    fun `products is populated when API returns data`() = runTest {
        // GIVEN
        val fakeResponse = listOf(
            ClothesItem(
                id = 1,
                name = "T-shirt",
                category = "tops",
                price = 10.0,
                originalPrice = 15.0,
                likes = 5,
                picture = Picture(url = "url.png", description = "desc")
            )
        )
        whenever(apiService.getDataClothes()).thenReturn(fakeResponse)

        // WHEN
        val viewModel = MainViewModel(apiService)

        // Attend que la coroutine du init se termine
        advanceUntilIdle()

        // THEN
        assertEquals(1, viewModel.products.size)
        val product = viewModel.products.first()
        assertEquals(1, product.id)
        assertEquals("T-shirt", product.title)
        assertEquals("Tops", product.category)
        product.oldPrice?.let { assertEquals(15.0, it, 0.0) }
    }

    @Test
    fun `oldPrice is null when originalPrice not equal price`() = runTest {
        val fakeResponse = listOf(
            ClothesItem(
                id = 2,
                name = "Sneakers",
                category = "shoes",
                price = 50.0,
                originalPrice = 50.0,
                likes = 10,
                picture = Picture(url = "shoes.png", description = "desc")
            )
        )
        whenever(apiService.getDataClothes()).thenReturn(fakeResponse)

        val viewModel = MainViewModel(apiService)

        advanceUntilIdle()

        val product = viewModel.products.first()
        assertNull(product.oldPrice)
    }

    @Test
    fun `products is empty when API throws exception`() = runTest {
        whenever(apiService.getDataClothes()).thenThrow(RuntimeException("Network error"))

        val viewModel = MainViewModel(apiService)

        advanceUntilIdle()

        assertTrue(viewModel.products.isEmpty())
    }
}