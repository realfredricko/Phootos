package com.example.unsplash.unsplash_features.presentation.search_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.unsplash.unsplash_features.data.model.Photo
import com.example.unsplash.unsplash_features.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _querySearch = mutableStateOf("")
    val querySearch: MutableState<String> get() = _querySearch

    private val _searchedPhotos = MutableStateFlow<PagingData<Photo>>(PagingData.empty())
    val searchedPhotos: StateFlow<PagingData<Photo>> get() = _searchedPhotos

    fun queryUpdate(query: String) {
        _querySearch.value = query
    }
//Updates search query and fetches the corresponding photos from
    //the repository
    fun searchUpdate(query: String) {
        viewModelScope.launch {
            repository.searchAllPhotos(query = query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchedPhotos.value = it
                }
        }
    }
}



