package com.example.unsplash.unsplash_features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unsplash.ui.theme.UnsplashTheme
import com.example.unsplash.unsplash_features.presentation.home_screen.HomeScreen
import com.example.unsplash.unsplash_features.presentation.search_screen.SearchScreen
import com.example.unsplash.unsplash_features.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//Will contain all the screens and components in which
//dependencies will be injected
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Phootos()
                }
            }
        }
    }
}

@Composable
fun Phootos() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}



