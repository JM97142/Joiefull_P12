package com.example.joiefull_p12.data.models

import com.google.gson.annotations.SerializedName

data class ClothesItem(
    val id: Int,
    val picture: Map<String, String>,
    val name: String,
    val category: String,
    val likes: Int,
    val price: Float,
    @SerializedName("original_price")
    val originalPrice: Float
)

typealias ClothesResponse = List<ClothesItem>