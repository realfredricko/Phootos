package com.example.unsplash.unsplash_features.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.network.PhotosAPI
import javax.inject.Inject

class SearchPagingSource(
    private val photosAPI: PhotosAPI,
    private val query:String
):PagingSource<Int, Photo>(){
/**Shows how to retrieve data from the corresponding dataSource*/
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
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
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition

        }
    }


