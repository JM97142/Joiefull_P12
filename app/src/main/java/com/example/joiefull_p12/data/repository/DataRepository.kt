package com.example.joiefull_p12.data.repository

import com.example.joiefull_p12.data.api.ClothesApiService
import com.example.joiefull_p12.data.models.ClothesItem
import com.example.joiefull_p12.data.models.ProductModel
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiService: ClothesApiService) {

    suspend fun getProducts()
    : List<ClothesItem> {
        return try {
            val products = apiService.getDataClothes()
            products
        }
        catch (e:Exception) {
            emptyList()
        }
    }
}