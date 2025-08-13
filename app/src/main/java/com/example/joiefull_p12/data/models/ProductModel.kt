package com.example.joiefull_p12.data.models

data class ProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val oldPrice: Double?,
    val rating: Float,
    val imageUrl: String,
    val description: String,
    val category: String
)