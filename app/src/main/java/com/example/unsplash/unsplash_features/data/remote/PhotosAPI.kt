package com.example.unsplash.unsplash_features.data.remote

import com.example.unsplash.BuildConfig
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.model.SearchResults
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosAPI {
    companion object {
         val apiKey = BuildConfig.API_KEY
    }
    /*@Headers("Authorization: Client-ID $API_KEY")*/
    @GET("/photos")
    suspend fun getAllPhotos(
        @Query("page:Int")page:Int,
        @Query("per_page:Int") perPage:Int = 10,
    ):List<Photo>

    @GET("/search")
    suspend fun searchAllPhotos(
        @Query("query:String")query:String,
        @Query("per_page:Int") perPage:Int = 10,
    ):SearchResults
}