package com.example.joiefull_p12.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.joiefull_p12.data.models.ProductModel

@Composable
fun ProductPrice(product: ProductModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 4.dp)
            .semantics {
                contentDescription = "Prix actuel : ${product.price}. Ancien prix : ${product.oldPrice}"
            }
    ) {
        Text(
            "${product.price}€",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "${product.oldPrice}€",
            fontSize = 16.sp,
            color = Color.Gray,
            textDecoration = TextDecoration.LineThrough
        )
    }
}