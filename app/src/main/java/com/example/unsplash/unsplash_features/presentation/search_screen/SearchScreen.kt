package com.example.unsplash.unsplash_features.presentation.search_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.util.query
import com.example.unsplash.unsplash_features.presentation.home_screen.component.HomeScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
navController: NavController,
vm: SearchViewModel = viewModel()
){
    val querySearch by SearchViewModel
    val searchedPhotos by SearchViewModel
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold (
            topBar = {
                SearchTopBar(text = querySearch,

                    onTextChange = {
                                   vm.SearchQueryUpdate(query = it)
                    },
                    onSearchClicked = {
vm.SearchUpdate(
    query = it
)
                },
                    onClosedClicked = {
                        navController.navigateUp()
                    }
                )
            },
            content = {
HomeScreenContent(flow = )
            }
        )
}}