package com.example.unsplash.unsplash_features.presentation.home_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.unsplash.unsplash_features.data.model.Photo

@Composable
fun PhotoList(photo: List<Photo>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(items = photo, key = {it.id}) { photo ->
            PhotoItem(photo)
            Divider(modifier = Modifier
                .height(8.dp))
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium),
            painter = rememberAsyncImagePainter(model = photo.urls.regular),
            contentDescription = "Photo",
            contentScale = ContentScale.Crop
        )
            Surface(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .alpha(0.5f),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .height(45.dp)
                        .padding(horizontal = 6.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    LikeCounter(
                        modifier = Modifier.weight(1f),
                        likes = "${photo.likes}"
                    )
                }
            }


        }
    }
@Composable
fun LikeCounter(
    modifier: Modifier,
    likes: String
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "likes",
            tint = Color.Red
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = likes,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }
}
