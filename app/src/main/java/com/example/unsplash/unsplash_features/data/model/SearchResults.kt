package com.example.unsplash.unsplash_features.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResults(
    val results:List<Photo>
)