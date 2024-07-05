package com.example.unsplash.injector

import android.content.Context
import androidx.room.Room
import com.example.unsplash.unsplash_features.data.local.PhotosDatabase
import com.example.unsplash.unsplash_features.utils.Constants.PHOTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//describes how to provide an instance to room database
@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Provides
    @Singleton
    fun createDatabase(
        @ApplicationContext context: Context
    ): PhotosDatabase {
        return Room.databaseBuilder(
            context,
            PhotosDatabase::class.java,
            PHOTO_DATABASE
        )
            .build()

    }
}
