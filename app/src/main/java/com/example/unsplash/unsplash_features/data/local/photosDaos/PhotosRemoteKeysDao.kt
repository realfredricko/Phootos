package com.example.unsplash.unsplash_features.data.local.photosDaos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unsplash.unsplash_features.data.model.PhotosRemoteKeys

/**Provides methods for the app to query,insert....
keys that are used by [PhotosRemoteMediator] to tell the backend service
which data to load next*/

@Dao
interface PhotosRemoteKeysDao {
        @Query("SELECT * FROM photo_remote_table WHERE id=:id")
        fun getRemoteKeys(id:String):PhotosRemoteKeys
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addAllRemoteKeys(remoteKeys: List<PhotosRemoteKeys>)
        @Query("DELETE FROM photo_remote_table")
        suspend fun clearRemoteKeys()
}