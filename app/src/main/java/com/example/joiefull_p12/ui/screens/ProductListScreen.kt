package com.example.joiefull_p12.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.joiefull_p12.data.models.ProductModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.joiefull_p12.ui.components.ProductCard

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductListScreen(
    products: List<ProductModel>,
    navController: NavController
) {
    // Définir ordre des catégories
    val productCategorie = listOf("Tops", "Bottoms", "Accessories", "Shoes")

    val nomAfficheCategorie = mapOf(
        "Tops" to "Hauts",
        "Bottoms" to "Bas",
        "Accessories" to "Accessoires",
        "Shoes" to "Chaussures"
    )
    // Grouper les produits par catégorie
    val grouped = products.groupBy { it.category }

    // Trier selon ordre défini
    val orderedGrouped = productCategorie.mapNotNull { category ->
        grouped[category]?.let { items -> category to items }
    } + grouped.filterKeys { it !in productCategorie }.toList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)) {
        orderedGrouped.forEach { (category, items) ->
            item {
                Text(
                    text = nomAfficheCategorie[category] ?: category,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                LazyRow {
                    items(items) { product ->
                        ProductCard(product = product) {
                            navController.navigate("detail/${product.id}")
                        }
                    }
                }
            }
        }
    }
}