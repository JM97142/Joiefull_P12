package com.example.joiefull_p12.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.joiefull_p12.data.models.ProductModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.joiefull_p12.ui.components.ProductCard

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