package com.example.unsplash.unsplash_features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.unsplash.unsplash_features.utils.Screens

@Composable
    fun AppNavigation(navController: NavController) {

        NavHost(navController = navController,
            startDestination = Screens.Home.route) {
            composable(route = Screens.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(route = Screens.Search.route) {
                SearchScreen(navController = navController)
            }
            // Add more composable functions for other destinations as needed
        }
    }
