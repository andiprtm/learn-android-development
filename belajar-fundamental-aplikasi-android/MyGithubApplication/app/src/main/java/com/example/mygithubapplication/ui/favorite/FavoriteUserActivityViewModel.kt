package com.example.mygithubapplication.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.mygithubapplication.repository.FavoriteRepository

class FavoriteUserActivityViewModel(private val favoriteRepository: FavoriteRepository) :
    ViewModel() {
    fun getAllFavorites() = favoriteRepository.getAllFavorites()
}