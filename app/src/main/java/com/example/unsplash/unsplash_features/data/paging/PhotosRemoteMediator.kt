package com.example.unsplash.unsplash_features.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.unsplash.unsplash_features.data.cache.PhotosDatabase
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.model.PhotosRemoteKeys
import com.example.unsplash.unsplash_features.data.network.PhotosAPI

//RemoteMediator acts as a signal when app runs out of cached data
// GET new data from the api and CACHE the data to the local database
//PagingSource load it to ui to display

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator (
    private val photosDatabase: PhotosDatabase,
    private val photosAPI: PhotosAPI,
) : RemoteMediator<Int, Photo>() {
    private val photosDao = photosDatabase.photosDao()
    private val photosRemoteKeysDao = photosDatabase.photosRemoteKeysDao()

    // Load method fetches new data from the api and saves it to the local database

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Photo>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                /**Used to refresh or initial load of a pagingData*/
                LoadType.REFRESH -> 1
/**Used to load at the start of a pagingData*/
                LoadType.PREPEND -> {
                    val remoteKeys = getInitialItemRemoteKey(state)
                    remoteKeys?.prePage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }
/**Used to load at the end of a pagingData*/
                LoadType.APPEND -> {
                    val remoteKeys = getLastItemRemoteKey(state)
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
                    photosDao.clearAllPhotos()
                    photosRemoteKeysDao.clearRemoteKeys()
                }

                val keys = response.map { photo ->
                    PhotosRemoteKeys(
                        id = photo.id,
                        prePage = prevPage,
                        nextPage = nextPage
                    )
                }

                photosDao.addPhotos(response)
                photosRemoteKeysDao.addAllRemoteKeys(keys)
            }

            MediatorResult.Success(endOfPaginationReached
            = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

/*private fun getRemoteKeyClosestToPosition(
    state: PagingState<Int, Photo>
):PhotosRemoteKeys?{
    return  state.anchorPosition?.let {
        position ->
        state.closestItemToPosition(position)?.id?.let { id ->
            photosRemoteKeysDao.getRemoteKeys(id)
        }
    }
}*/


private fun getInitialItemRemoteKey(
    state: PagingState<Int, Photo>
): PhotosRemoteKeys? {
    return state.pages.firstOrNull(){
        it.data.isNotEmpty()}?.data?.firstOrNull()?.let {
            photo->
        photosRemoteKeysDao.getRemoteKeys(photo.id)
    }
}

private fun getLastItemRemoteKey(
    state: PagingState<Int, Photo>
): PhotosRemoteKeys? {
    return  state.pages.lastOrNull{
        it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let {
            photo ->
        photosRemoteKeysDao.getRemoteKeys(id = photo.id)
    }
}
}




