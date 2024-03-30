package com.example.mygithubapplication.ui.helper.application

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubapplication.di.Injection
import com.example.mygithubapplication.repository.FavoriteRepository
import com.example.mygithubapplication.ui.detail.DetailActivityViewModel
import com.example.mygithubapplication.ui.favorite.FavoriteUserActivityViewModel

class ViewModelFactory private constructor(private val userRepository: FavoriteRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteUserActivityViewModel::class.java)) {
            return FavoriteUserActivityViewModel(userRepository) as T
        } else if (modelClass.isAssignableFrom(DetailActivityViewModel::class.java)) {
            return DetailActivityViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideFavoriteUserRepository(context))
            }.also { instance = it }
    }
}