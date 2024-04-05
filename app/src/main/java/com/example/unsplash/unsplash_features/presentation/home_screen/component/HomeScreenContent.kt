package com.example.unsplash.unsplash_features.presentation.home_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.unsplash.unsplash_features.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Composable
/*fun HomeScreenContent(items: LazyPagingItems<Photo>) {
    Log.d("Error", items.loadState.toString())

    {
        items(
            items
            ,

        ) {
        }
    }
}/
 */
fun HomeScreenContent(flow:Flow<PagingData<Photo>>){
   val lazyPagingItems = flow.collectAsLazyPagingItems()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(all = 12.dp),
        modifier = Modifier.fillMaxSize()
    ){
        items(count = lazyPagingItems.itemCount,
            key = { photo ->
                photo?: throw IllegalArgumentException("Item key should not be null")
            }){
                photo ->
        photo?.let { PhotoItem(photo = photo) }}
        }
    }
