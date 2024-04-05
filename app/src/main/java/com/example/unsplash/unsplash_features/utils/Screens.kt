package com.example.unsplash.unsplash_features.utils

sealed class Screens(val route:String) {
    data object Home :Screens("home_screen")
    data object Search:Screens("search_screen")
    data object Bookmark:Screens("bookmark_screen")
}