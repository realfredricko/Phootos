package com.example.unsplash.unsplash_features.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unsplash.unsplash_features.data.cache.photosDaos.PhotosDao
import com.example.unsplash.unsplash_features.data.cache.photosDaos.PhotosRemoteKeysDao
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.model.PhotosRemoteKeys

//Data entities represent tables in the app database
//Database class provides the app with instances of the DAOs.
//The app uses the DAOs to retrieve data from the database
// as instances of the associated data entity
@Database(entities = [Photo::class,PhotosRemoteKeys::class], version = 1)
abstract class PhotosDatabase:RoomDatabase() {
    abstract fun photosDao():PhotosDao
    abstract fun photosRemoteKeysDao():PhotosRemoteKeysDao
}