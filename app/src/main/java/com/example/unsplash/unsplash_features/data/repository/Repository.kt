package com.example.unsplash.unsplash_features.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.unsplash.unsplash_features.data.local.PhotosDatabase
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.model.SearchResults
import com.example.unsplash.unsplash_features.data.paging.PhotosRemoteMediator
import com.example.unsplash.unsplash_features.data.paging.SearchPagingSource
import com.example.unsplash.unsplash_features.data.remote.PhotosAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val photosAPI:PhotosAPI,
    private val photosDatabase: PhotosDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllPhotos(): Flow<PagingData<Photo>> {
val pagingSourceFactory = {
    photosDatabase.unsplashPhotosDao().insertAllPhotos()
}
        return  Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PhotosRemoteMediator(
                photosAPI = photosAPI,
                photosDatabase = photosDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

         fun getAllPhotos(page: Int, perPage: Int): List<Photo> {
            TODO("Not yet implemented")
        }

        fun searchAllPhotos(query: String): Flow<PagingData<PhotosAPI>>{
            return Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = {
                    SearchPagingSource(photosAPI = photosAPI,query = query)
                }
            ).flow
        }
    }
}