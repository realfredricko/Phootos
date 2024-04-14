package com.example.unsplash.unsplash_features.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsplash.unsplash_features.data.remote.PhotosAPI
import com.example.unsplash.unsplash_features.utils.Constants.PHOTOS_TABLE
import kotlinx.serialization.Serializable
@Serializable
@Entity(tableName = PHOTOS_TABLE)
data class Photo(
    @PrimaryKey(autoGenerate = false)
    @Embedded
    val id:String,
    val urls:PhotoUrls,
    val likes:Int,
    val color:String,
    val user:PhotoCreator,
val description:String?,
    val alternateDescription:String?
) : PhotosAPI {
    override suspend fun getAllPhotos(page: Int, perPage: Int): List<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun searchAllPhotos(query: String, perPage: Int): SearchResults {
        TODO("Not yet implemented")
    }
}
