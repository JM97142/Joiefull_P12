package com.example.joiefull_p12.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.joiefull_p12.data.models.ProductModel
import com.example.joiefull_p12.ui.components.ProductImageHeader
import com.example.joiefull_p12.ui.components.ProductPrice
import com.example.joiefull_p12.ui.components.ProductReview
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
            .padding(top = 16.dp)
    ) {
        // Image principale avec boutons overlay
        ProductImageHeader(product, navController)
        Spacer(modifier = Modifier.height(16.dp))
        // Titre + note
        ProductTitleRating(product)
        // Prix
        ProductPrice(product)
        Spacer(modifier = Modifier.height(12.dp))
        // Description
        Text(
            text = product.description,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.semantics {
                contentDescription = "Description du produit : ${product.description}"
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // User review
        ProductReview()
    }
}