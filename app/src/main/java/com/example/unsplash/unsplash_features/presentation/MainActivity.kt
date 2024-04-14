package com.example.unsplash.unsplash_features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.unsplash.ui.theme.UnsplashTheme
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
                ){
                    val navController = rememberNavController()
                    NavHost(navController = navController ,
                        startDestination = Screens.Home.route,
                    ){
                        composable(route = Screens.Home.route) {
                            HomeScreen(navController = navController)
                        }

                        composable(route = Screens.Search.route) {
                            SearchScreen(navController = navController)
                        }
                    }
                    //AppNavigation(navController = navController)

            }
        }
    }
}}



