package com.example.joiefull_p12.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.joiefull_p12.data.models.ProductModel
import com.example.joiefull_p12.ui.components.ProductImageHeader
import com.example.joiefull_p12.ui.components.ProductTitleRating

@Composable
fun ProductDetailScreen(
    product: ProductModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Image principale avec boutons overlay
        ProductImageHeader(product, navController)
        Spacer(modifier = Modifier.height(16.dp))
        // Titre + note
        ProductTitleRating(product)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductDetailScreen() {
    val fakeProduct = ProductModel(
        id = 1,
        title = "Pull torsadé",
        price = 69.0,
        oldPrice = 95.0,
        description = "Pull vert forêt à motif torsadé élégant, tricot finement travaillé avec manches bouffantes et col montant; doux et chaleureux.",
        imageUrl = "",
        rating = 4.6f,
        category = "Tops"
    )

    ProductDetailScreen(
        product = fakeProduct,
        navController = rememberNavController()
    )
}
