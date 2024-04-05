package com.example.unsplash.unsplash_features.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.unsplash.unsplash_features.data.local.PhotosDatabase
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.model.PhotosRemoteKeys
import com.example.unsplash.unsplash_features.data.remote.PhotosAPI
import javax.inject.Inject

//RemoteMediator acts as a signal when app runs out of cached data
// GET new data from the api and CACHE the data to the local database
//PagingSource load it to ui to display

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator (
    private val photosDatabase: PhotosDatabase,
    private val photosAPI: PhotosAPI
) : RemoteMediator<Int, Photo>() {
    private val unsplashPhotosDao = photosDatabase.unsplashPhotosDao()
    private val unsplashRemoteKeysDao = photosDatabase.unsplashRemoteKeysDao()

    // Load method fetches new data from the api and saves it to the local database

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Photo>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> 1

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyInitialItem(state)
                    remoteKeys?.prePage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyLastItem(state)
                    remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }
            }

            val response = photosAPI.getAllPhotos(page = currentPage, perPage = 10)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            photosDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashPhotosDao.clearAllPhotos()
                    unsplashRemoteKeysDao.clearRemoteKeys()
                }

                val keys = response.map { photo ->
                    PhotosRemoteKeys(
                        id = photo.id,
                        prePage = prevPage,
                        nextPage = nextPage
                    )
                }

                unsplashPhotosDao.addPhotos(response)
                unsplashRemoteKeysDao.addAllRemoteKeys(keys)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
private  suspend fun getRemoteKeyClosestToPosition(
    state: PagingState<Int, Photo>
):PhotosRemoteKeys?{
    return  state.anchorPosition?.let {
        position ->
        state.closestItemToPosition(position)?.id?.let { id ->
            unsplashRemoteKeysDao.getRemoteKeys(id = id)
        }
    }
}


private suspend fun getRemoteKeyInitialItem(
    state: PagingState<Int, Photo>
): PhotosRemoteKeys? {
    return  state.pages.firstOrNull{
        it.data.isNotEmpty()
    }?.data?.firstOrNull()?.let {
        photo ->
        unsplashRemoteKeysDao.getRemoteKeys(id = photo.id)
    }
}

private suspend fun getRemoteKeyLastItem(
    state: PagingState<Int, Photo>
): PhotosRemoteKeys? {
    return  state.pages.lastOrNull{
        it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let {
            photo ->
        unsplashRemoteKeysDao.getRemoteKeys(id = photo.id)
    }
}
}




