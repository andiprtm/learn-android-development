package com.example.mygithubapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.mygithubapplication.data.Result
import com.example.mygithubapplication.data.favorite.entity.UserEntity
import com.example.mygithubapplication.data.favorite.room.UserDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository private constructor(private val userDao: UserDao) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllFavorites(): LiveData<Result<List<UserEntity>>> {
        Result.Loading
        return userDao.getAllUser().map { userList ->
            Result.Success(userList)
        }
    }

    fun insert(user: UserEntity) {
        executorService.execute {
            try {
                userDao.insert(user)
            } catch (e: Exception) {
                Result.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun delete(id: Int) {
        executorService.execute {
            try {
                userDao.removeFavorite(id)
            } catch (e: Exception) {
                Result.Error(e.message ?: "An error occurred")
            }
        }
    }

    companion object {
        @Volatile
        private var instance: FavoriteRepository? = null
        fun getInstance(
            userDao: UserDao
        ): FavoriteRepository =
            instance ?: synchronized(this) {
                instance ?: FavoriteRepository(userDao)
            }.also { instance = it }
    }

}