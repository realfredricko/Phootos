package com.example.unsplash.unsplash_features.presentation.search_screen

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.unsplash.ui.theme.UnsplashTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    text:String,
    onSearchClicked:(String)->Unit,
    //active:Boolean
    modifier:Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    onTextChange:(String) -> Unit = {},
    onClosedClicked:() -> Unit
)
{
TextField(modifier = Modifier
    .fillMaxWidth()
    .background(color = MaterialTheme.colorScheme.background, shape = RectangleShape)
    .semantics {
        contentDescription = "TextField"
               },
    value = text,
    onValueChange ={
    onTextChange(it)},
    placeholder = {
        Text(text = "Search...",
            modifier = Modifier
                .alpha(alpha = 0.5f),
            color = Color.White
        )
    },
    textStyle = MaterialTheme.typography.headlineSmall,
    singleLine = true,
    maxLines = 1,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription ="Search Icon" ,
            tint = MaterialTheme.colorScheme.onBackground
            )
    },
    trailingIcon = {
        IconButton(onClick = {
if (text.isNotBlank()){
    onTextChange("")
}
            else{
                onClosedClicked()
            }
        }) {
            Icon(imageVector = Icons.Default.Clear,
                contentDescription = "Clear Icon",
                tint = MaterialTheme.colorScheme.onBackground
                )
        }
    }

)

    }
@Composable
@Preview(showBackground = true)
fun SearchTopBarPreview(){
UnsplashTheme {
    SearchTopBar(text = "Mango", onSearchClicked ={}  ) {
        
    }
}
}




