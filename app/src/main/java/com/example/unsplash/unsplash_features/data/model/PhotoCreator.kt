package com.example.unsplash.unsplash_features.data.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class PhotoCreator(
@SerializedName("links")
    val name:String,
    val username:String,
@Embedded
@Contextual
    val userLinks:PhotoCreatorLinks
)
