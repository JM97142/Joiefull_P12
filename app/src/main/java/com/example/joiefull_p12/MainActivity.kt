package com.example.joiefull_p12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.joiefull_p12.data.models.ProductModel
import com.example.joiefull_p12.ui.screens.ProductListScreen
import com.example.joiefull_p12.ui.screens.SplashScreen
import com.example.joiefull_p12.ui.theme.Joiefull_P12Theme
import com.example.joiefull_p12.ui.viewmodels.MainViewModel
import com.example.joiefull_p12.ui.screens.ProductDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Joiefull_P12Theme {
                JoiefullApp()
            }
        }
    }
}

@Composable
fun JoiefullApp() {
    val navController = rememberNavController()
    val vm: MainViewModel = hiltViewModel()
    val isTablet = androidx.compose.ui.platform.LocalConfiguration.current.screenWidthDp > 600

    MaterialTheme {
        NavHost(navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController)
            }
            composable("list") {
                if (isTablet) {
                    // Tablet navigation
                    var selectedProduct by remember { mutableStateOf(vm.products.firstOrNull()) }

                    Row(Modifier.fillMaxSize()) {
                        androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                            ProductListScreen(
                                products = vm.products,
                                onProductClick = { product ->
                                    selectedProduct = product
                                }
                            )
                        }

                        // DÉTAIL
                        androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                            selectedProduct?.let { product ->
                                ProductDetailScreen(
                                    product = product,
                                    navController = navController
                                )
                            }
                        }
                    }
                } else {
                    // Mobile navigation
                    ProductListScreen(
                        products = vm.products,
                        onProductClick = { product ->
                            navController.navigate("detail/${product.id}")
                        }
                    )
                }
            }
            composable(
                "detail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                val product = vm.products.find { it.id == id }
                product?.let {
                    ProductDetailScreen(product = it, navController = navController)
                }
            }
        }
    }
}


@Preview(name = "Mobile", showBackground = true, widthDp = 360, heightDp = 800, apiLevel = 34)
@Composable
fun PreviewProductDetailMobile() {
    val fakeProduct = ProductModel(
        id = 1,
        title = "T-shirt oversize",
        description = "Un T-shirt oversize confortable et stylé.",
        price = 19.99,
        oldPrice = 29.99,
        imageUrl = "https://via.placeholder.com/400",
        category = "Tops",
        rating = 4.5f
    )
    ProductDetailScreen(
        product = fakeProduct,
        navController = rememberNavController()
    )
}

@Preview(name = "Tablette", showBackground = true, widthDp = 1280, heightDp = 800, apiLevel = 34)
@Composable
fun PreviewProductDetailTablet() {
    val fakeProduct = ProductModel(
        id = 1,
        title = "T-shirt oversize",
        description = "Un T-shirt oversize confortable et stylé.",
        price = 19.99,
        oldPrice = 29.99,
        imageUrl = "https://via.placeholder.com/400",
        category = "Tops",
        rating = 4.5f
    )
    ProductDetailScreen(
        product = fakeProduct,
        navController = rememberNavController()
    )
}
