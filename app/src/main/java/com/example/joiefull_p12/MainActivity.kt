package com.example.joiefull_p12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.joiefull_p12.ui.screens.ProductDetailScreen
import com.example.joiefull_p12.ui.screens.ProductListScreen
import com.example.joiefull_p12.ui.screens.SplashScreen
import com.example.joiefull_p12.ui.theme.Joiefull_P12Theme
import com.example.joiefull_p12.ui.viewmodels.MainViewModel
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

    MaterialTheme {
        NavHost(navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController)
            }
            composable("list") {
                ProductListScreen(vm.products, navController)
            }
            composable(
                "detail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                val product = vm.products.find { it.id == id }
                product?.let {
                    ProductDetailScreen(it, navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Joiefull_P12Theme {
        JoiefullApp()
    }
}