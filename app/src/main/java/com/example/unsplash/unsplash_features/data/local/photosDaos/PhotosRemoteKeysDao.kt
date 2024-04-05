package com.example.unsplash.unsplash_features.data.local.photosDaos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unsplash.unsplash_features.data.model.PhotosRemoteKeys

//Provides methods for the app to query,insert....
@Dao
interface PhotosRemoteKeysDao {
        @Query("SELECT * FROM unsplash_remote_table WHERE id=:id")
        fun getRemoteKeys(id:String):PhotosRemoteKeys
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addAllRemoteKeys(remoteKeys: List<PhotosRemoteKeys>)
        @Query("DELETE FROM unsplash_remote_table")
        suspend fun clearRemoteKeys()
}