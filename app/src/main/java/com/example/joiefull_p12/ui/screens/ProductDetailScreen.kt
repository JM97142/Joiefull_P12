package com.example.joiefull_p12.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.joiefull_p12.data.models.ProductModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    productModel: ProductModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GlideImage(
            model = productModel.imageUrl,
            contentDescription = productModel.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
                .padding(vertical = 16.dp)
        )
        Button(
            onClick = {
                navController.popBackStack()
            }) {
            Text("Back")
        }
    }
}