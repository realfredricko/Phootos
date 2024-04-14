package com.example.unsplash.unsplash_features.data.local.photosDaos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unsplash.unsplash_features.data.model.Photo

//Provides methods for the app to query,insert....
@Dao
interface PhotosDao {
        @Query("SELECT * FROM photos_table")
        fun getAllPhotos():PagingSource<Int,Photo>
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addPhotos(photos: List<Photo>)
        @Query("DELETE FROM photos_table")
        suspend fun clearAllPhotos()

}