package com.example.joiefull_p12.data.api

import com.example.joiefull_p12.data.models.ClothesResponse
import retrofit2.http.GET


interface ClothesApiService {
    @GET("clothes.json")
    suspend fun getDataClothes(): ClothesResponse
}