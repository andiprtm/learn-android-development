package com.example.mygithubapplication.di

import android.content.Context
import com.example.mygithubapplication.data.favorite.room.UserRoomDatabase
import com.example.mygithubapplication.repository.FavoriteRepository

object Injection {
    fun provideFavoriteUserRepository(context: Context): FavoriteRepository {
        val database = UserRoomDatabase.getDatabase(context)
        val dao = database.userDao()
        return FavoriteRepository.getInstance(dao)
    }
}