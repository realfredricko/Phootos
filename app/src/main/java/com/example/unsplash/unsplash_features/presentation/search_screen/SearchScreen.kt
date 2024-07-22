package com.example.unsplash.unsplash_features.presentation.search_screen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.unsplash.unsplash_features.presentation.home_screen.component.PhotoList
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = viewModel()
) {
    val querySearch by searchViewModel.querySearch
    val searchedPhotos = searchViewModel.searchedPhotos

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                SearchTopBar(
                    text = querySearch,
                    onTextChange = { searchViewModel.queryUpdate(query = it) },
                    onSearchClicked = { searchViewModel.searchUpdate(query = querySearch) },
                    onClosedClicked = { navController.navigateUp() }
                )
            },
            content = {
                PhotoList(photos = searchedPhotos)
            }
        )
    }
}