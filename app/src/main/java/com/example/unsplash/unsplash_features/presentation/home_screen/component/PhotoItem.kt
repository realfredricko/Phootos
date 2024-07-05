package com.example.unsplash.unsplash_features.presentation.home_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.unsplash.unsplash_features.data.model.Photo

@Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

@Composable
fun PhotoList(
    photos: LazyPagingItems<Photo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = photos,
            key = { it.id }
        ) { photo ->
            photo?.let {
                PhotoItem(photo = it)
                Divider(thickness = 8.dp)
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium),
            painter = rememberAsyncImagePainter(model = photo.urls.regular),
            contentDescription = "Photo",
            contentScale = ContentScale.Crop
        )
    }
}
