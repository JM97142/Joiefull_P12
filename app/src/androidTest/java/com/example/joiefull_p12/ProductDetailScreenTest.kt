package com.example.joiefull_p12

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.joiefull_p12.data.models.ProductModel
import com.example.joiefull_p12.ui.screens.ProductDetailScreen
import org.junit.Rule
import org.junit.Test

class ProductDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeProduct = ProductModel(
        id = 1,
        title = "T-shirt",
        imageUrl = "url.png",
        description = "Un super T-shirt confortable",
        category = "Tops",
        price = 19.99,
        oldPrice = 25.0,
        likes = 10
    )

    @Test
    fun productDetails_areDisplayed() {
        composeTestRule.setContent {
            ProductDetailScreen(product = fakeProduct, navController = rememberNavController())
        }

        // Vérifie le titre
        composeTestRule.onNodeWithText("T-shirt").assertIsDisplayed()
        // Vérifie la description
        composeTestRule.onNodeWithText("Un super T-shirt confortable").assertIsDisplayed()
        // Vérifie que le prix apparaît
        composeTestRule.onNodeWithText("19.99€").assertExists()
    }

    @Test
    fun description_hasContentDescription_forAccessibility() {
        composeTestRule.setContent {
            ProductDetailScreen(product = fakeProduct, navController = rememberNavController())
        }

        // Vérifie que le noeud avec la description est accessible via contentDescription
        composeTestRule.onNode(
            hasContentDescription("Description du produit : ${fakeProduct.description}")
        ).assertExists()
    }
}
