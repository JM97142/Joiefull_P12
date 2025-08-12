package com.example.joiefull_p12.data.api

import retrofit2.http.GET

data class ClothesResponse(
    val id: Int,
    val picture: Map<String, String>,
    val name: String,
    val category: String,
    val likes: Double,
    val price: Float,
    val original_price: Float
)

interface ClothesApiService {
    @GET("clothes.json")
    suspend fun getDataClothes(): ClothesResponse
}