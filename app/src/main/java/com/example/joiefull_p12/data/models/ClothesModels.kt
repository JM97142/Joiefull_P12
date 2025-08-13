package com.example.joiefull_p12.data.models

import com.google.gson.annotations.SerializedName

data class ClothesItem(
    val id: Int,
    val picture: Picture,
    val name: String,
    val category: String,
    val likes: Int,
    val price: Double,
    @SerializedName("original_price")
    val originalPrice: Double
)

data class Picture(
    val url: String,
    val description: String
)

typealias ClothesResponse = List<ClothesItem>