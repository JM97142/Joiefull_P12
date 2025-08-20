package com.example.joiefull_p12.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joiefull_p12.data.api.ClothesApiService
import com.example.joiefull_p12.data.models.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ClothesApiService
) : ViewModel() {
    private val _products = mutableStateListOf<ProductModel>()
    val products: List<ProductModel> get() = _products

    init {
        viewModelScope.launch {
            try {
                val response = apiService.getDataClothes()
                _products.clear()
                _products.addAll(response.map { item ->
                    ProductModel(
                        id = item.id,
                        title = item.name,
                        imageUrl = item.picture.url,
                        description = item.picture.description,
                        category = item.category.lowercase().replaceFirstChar { it.uppercase() },
                        price = item.price,
                        oldPrice = if (item.originalPrice > item.price) item.originalPrice else null,
                        likes = item.likes
                    )
                })
            } catch (e: Exception) {
                Log.e("API", "Erreur : ${e.message}")
            }
        }
    }
}