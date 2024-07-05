package com.example.unsplash.unsplash_features.presentation.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.unsplash.unsplash_features.presentation.home_screen.component.PhotoList
import com.example.unsplash.unsplash_features.utils.Screens

@Composable
fun HomeBottomAppBar(onHomeClicked: () -> Unit, onSearchClicked: () -> Unit) {
    BottomAppBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        containerColor = Color(0XFFFF9800)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier.clickable { onHomeClicked() }
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.clickable { onSearchClicked() }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val photos = homeViewModel.getAllPhotos
    Scaffold(
        bottomBar = {
            HomeBottomAppBar(
                onHomeClicked = { navController.navigate(Screens.Home.route) },
                onSearchClicked = { navController.navigate(Screens.Search.route) }
            )
        },
        content = {
            PhotoList(photos = photos)
        }
    )
}


