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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

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

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        orderedGrouped.forEach { (category, items) ->
            item {
                Text(
                    text = nomAfficheCategorie[category] ?: category,
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
                                .width(198.dp)
                                .clickable {
                                    navController.navigate("detail/${product.id}")
                                },
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(198.dp)
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(80)
                                    )
                            ) {
                                GlideImage(
                                    model = product.imageUrl,
                                    contentDescription = product.description,
                                    contentScale = ContentScale.Crop
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(8.dp)
                                        .background(
                                            color = Color.White,
                                            shape = RoundedCornerShape(50)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Likes",
                                        tint = Color.Black,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = product.rating.toString(),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Black
                                    )
                                }
                            }
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(8.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${ product.price }€",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "${ product.oldPrice }€",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}