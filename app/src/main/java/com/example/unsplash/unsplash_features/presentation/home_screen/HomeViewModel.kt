package com.example.unsplash.unsplash_features.presentation.home_screen

import androidx.lifecycle.ViewModel
import com.example.unsplash.unsplash_features.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//inject the repository
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
):ViewModel(){
    val getAllPhotos = repository.getAllPhotos()
}