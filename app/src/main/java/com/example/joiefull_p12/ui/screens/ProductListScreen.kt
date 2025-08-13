package com.example.joiefull_p12.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductListScreen(products: List<ProductModel>, navController: NavController) {
    // Définir ordre des catégories
    val productCategorie = listOf("Tops", "Bottoms", "Accessories", "Shoes")

    // Grouper les produits par catégorie
    val grouped = products.groupBy { it.category }

    // Trier selon ordre défini
    val orderedGrouped = productCategorie.mapNotNull { category ->
        grouped[category]?.let { items -> category to items }
    } + grouped.filterKeys { it !in productCategorie }.toList()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        orderedGrouped.forEach { (category, items) ->
            item {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
            item {
                LazyRow {
                    items(items) { product ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .width(150.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            GlideImage(
                                model = product.imageUrl,
                                contentDescription = product.description,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                            )
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}