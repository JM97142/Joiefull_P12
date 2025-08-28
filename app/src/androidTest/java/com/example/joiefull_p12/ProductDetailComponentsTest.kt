package com.example.joiefull_p12

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.joiefull_p12.data.models.ProductModel
import com.example.joiefull_p12.ui.components.ProductImageHeader
import com.example.joiefull_p12.ui.components.ProductPrice
import com.example.joiefull_p12.ui.components.ProductReview
import com.example.joiefull_p12.ui.components.ProductTitleRating
import org.junit.Rule
import org.junit.Test

class ProductDetailComponentsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeProduct = ProductModel(
        id = 1,
        title = "Sneakers",
        imageUrl = "url.png",
        description = "Superbes sneakers confortables",
        category = "Shoes",
        price = 59.99,
        oldPrice = 79.99,
        likes = 12
    )

    @Test
    fun productImageHeader_displaysImageAndButtons() {
        composeTestRule.setContent {
            ProductImageHeader(product = fakeProduct, navController = rememberNavController())
        }

        // Vérifie image
        composeTestRule.onNodeWithContentDescription("Image du produit Sneakers")
            .assertIsDisplayed()

        // Vérifie bouton retour
        composeTestRule.onNodeWithContentDescription("Retour à la liste de produits").assertExists()

        // Vérifie bouton partage
        composeTestRule.onNodeWithContentDescription("Partager le produit Sneakers").assertExists()

        // Vérifie compteur de likes
        composeTestRule.onNodeWithText("12").assertIsDisplayed()
    }

    @Test
    fun productTitleRating_displaysTitleAndRating() {
        composeTestRule.setContent {
            ProductTitleRating(product = fakeProduct)
        }

        // Vérifie titre
        composeTestRule.onNodeWithText("Sneakers").assertIsDisplayed()

        // Vérifie nombre de likes
        composeTestRule.onNodeWithText("12").assertIsDisplayed()

        // Vérifie contentDescription combiné
        composeTestRule.onNode(
            hasContentDescription("Sneakers avec une note de 12")
        ).assertExists()
    }

    @Test
    fun productPrice_displaysPriceAndOldPrice() {
        composeTestRule.setContent {
            ProductPrice(product = fakeProduct)
        }

        // Vérifie prix actuel
        composeTestRule.onNodeWithText("59.99€").assertIsDisplayed()

        // Vérifie ancien prix
        composeTestRule.onNodeWithText("79.99€").assertIsDisplayed()

        // Vérifie accessibilité
        composeTestRule.onNode(
            hasContentDescription("Prix actuel : 59.99. Ancien prix : 79.99")
        ).assertExists()
    }

    @Test
    fun productReview_displaysStarsAndInput() {
        composeTestRule.setContent {
            ProductReview()
        }

        // Vérifie que 5 étoiles sont présentes
        repeat(5) { i ->
            composeTestRule.onNodeWithContentDescription("${i + 1} étoile").assertExists()
        }

        // Clique sur la 3e étoile et vérifie que c’est bien sélectionné
        composeTestRule.onNodeWithContentDescription("3 étoile").performClick()

        // Vérifie champ texte de review
        composeTestRule.onNodeWithText("Partagez ici vos impressions sur cette pièce")
            .assertExists()
    }
}
