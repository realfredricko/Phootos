package com.example.unsplash.unsplash_features.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplash.unsplash_features.data.remote.PhotosAPI
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val photosAPI: PhotosAPI,
    private val query:String
):PagingSource<Int,PhotosAPI>(){
/**Shows how to retrieve data from the corresponding dataSource*/
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosAPI> {
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
/**provides  a key used for the initial load for the pagingSource due
to the invalidation of the existing pagingSource.The key
 is provided to load via LoadParams.key.The last accessed position can be retrieved via
 state.anchorPosition
*/
    override fun getRefreshKey(state: PagingState<Int, PhotosAPI>): Int? {
        return state.anchorPosition
        }
    }

