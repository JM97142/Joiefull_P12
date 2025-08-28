package com.example.joiefull_p12

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.joiefull_p12.data.models.ProductModel
import com.example.joiefull_p12.ui.screens.ProductListScreen
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ProductListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeProducts = listOf(
        ProductModel(
            id = 1,
            title = "T-shirt",
            imageUrl = "url.png",
            description = "desc",
            category = "Tops",
            price = 10.0,
            oldPrice = 25.0,
            likes = 5
        ),
        ProductModel(
            id = 1,
            title = "Sneakers",
            imageUrl = "url.png",
            description = "desc",
            category = "Shoes",
            price = 50.0,
            oldPrice = null,
            likes = 3
        )
    )

    @Test
    fun categories_areDisplayedInOrder() {
        composeTestRule.setContent {
            ProductListScreen(products = fakeProducts, onProductClick = {})
        }

        // Vérifie que les catégories traduites sont affichées
        composeTestRule.onNodeWithText("Hauts").assertIsDisplayed()
        composeTestRule.onNodeWithText("Chaussures").assertIsDisplayed()

        // Vérifie l'ordre
        composeTestRule.onAllNodesWithText("Hauts")
            .onFirst()
            .assertExists()
        composeTestRule.onAllNodesWithText("Chaussures")
            .onFirst()
            .assertExists()
    }

    @Test
    fun products_areDisplayedUnderTheirCategory() {
        composeTestRule.setContent {
            ProductListScreen(products = fakeProducts, onProductClick = {})
        }

        // Vérifie que le titre des produits apparaît
        composeTestRule.onNodeWithText("T-shirt").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sneakers").assertIsDisplayed()
    }

    @Test
    fun clickingOnProduct_callsCallback() {
        var clickedProduct: ProductModel? = null

        composeTestRule.setContent {
            ProductListScreen(products = fakeProducts, onProductClick = { clickedProduct = it })
        }

        // Clique sur un produit
        composeTestRule.onNodeWithText("T-shirt").performClick()

        // Vérifie que le callback a bien reçu le bon produit
        assert(clickedProduct?.id == 1)
    }
}
