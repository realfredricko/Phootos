package com.example.unsplash.unsplash_features.presentation.home_screen.component
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter
import com.example.unsplash.unsplash_features.data.model.Photo

//data class Photo(val id: Int, val urls: Urls, val user: User, val likes: Int)

//data class Urls(val regular: String)

//data class User(val username: String)

@Composable
fun PhotoList(photo: List<Photo>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = photo, key = {it.id}) { photo ->
            PhotoItem(photo)
            Divider() // Add a divider between items if needed
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
        Box(
            modifier = Modifier
                .clickable {
                    val webIntent: Intent = Uri
                        .parse(
                            "https://unsplash.com/@${photo.user.username}" +
                                    "?utm_source=PhotoBuilder&utm_medium=referral"
                        )
                        .let { webpage ->
                            Intent(Intent.ACTION_VIEW, webpage)
                        }
                    startActivity(intent)
                }
                .height(300.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {


            Surface(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .alpha(0.5f),
                color = Color.Black
            ) {
                // Add any overlay UI elements here if needed
            }

            Row(
                modifier = Modifier
                    .height(45.dp)
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Photo by: ")
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold)
                        ) {
                            append(photo.user.username)
                        }
                },
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                    )
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
