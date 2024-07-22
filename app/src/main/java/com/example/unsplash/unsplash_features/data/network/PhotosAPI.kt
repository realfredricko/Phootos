package com.example.unsplash.unsplash_features.data.network

import com.example.unsplash.BuildConfig
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.model.SearchResults
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {
    @GET("/photos")
    suspend fun getAllPhotos(
        @Query("page:Int")page:Int,
        @Query("per_page:Int") perPage:Int = 10,
        @Query("api_key") apiKey:String = BuildConfig.API_KEY
    ):List<Photo>

    @GET("/search")
    suspend fun searchAllPhotos(
        @Query("query:String")query:String,
        @Query("per_page:Int") perPage:Int = 10,
        @Query("api_key") apiKey:String = BuildConfig.API_KEY
    ):SearchResults
}