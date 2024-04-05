package com.example.unsplash.unsplash_features.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplash.unsplash_features.data.remote.PhotosAPI
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val photosAPI: PhotosAPI,
    private val query:String
):PagingSource<Int,PhotosAPI>(){
//Shows how to retrieve data from the corresponding data source
    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, PhotosAPI> {
val currentPage = params.key?:1

    return  try {
val response = photosAPI.searchAllPhotos(query = query, perPage = 10)
        val endOfPaginationReached = response.results.isEmpty()
        if(response.results.isNotEmpty()){
            LoadResult.Page(
data = response.results,
                prevKey = if (currentPage==1) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        }
        else{
            LoadResult.Page(
                data = emptyList(),
prevKey = null,
                nextKey = null
                )
        }
    }
    catch (e: Exception){
        LoadResult.Error(e)
    }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotosAPI>): Int? {
        return state.anchorPosition
    }

}