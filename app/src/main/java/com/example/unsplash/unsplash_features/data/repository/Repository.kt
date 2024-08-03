package com.example.unsplash.unsplash_features.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.unsplash.unsplash_features.data.cache.PhotosDatabase
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.paging.PhotosRemoteMediator
import com.example.unsplash.unsplash_features.data.paging.SearchPagingSource
import com.example.unsplash.unsplash_features.data.network.PhotosAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val photosAPI:PhotosAPI,
    private val photosDatabase: PhotosDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllPhotos(): Flow<PagingData<Photo>> {
val pagingSourceFactory = {
    photosDatabase.photosDao().getAllPhotos()
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

        fun searchAllPhotos(query: String): Flow<PagingData<Photo>>{
            return Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = {
                    SearchPagingSource(photosAPI = photosAPI,query = query)
                }
            ).flow
            }
        }

