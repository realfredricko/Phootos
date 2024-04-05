package com.example.unsplash.unsplash_features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import com.example.unsplash.ui.theme.UnsplashTheme
import com.example.unsplash.unsplash_features.navigation.AppNavigation
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
                    AppNavigation(navController = navController)

            }
        }
    }
}}



