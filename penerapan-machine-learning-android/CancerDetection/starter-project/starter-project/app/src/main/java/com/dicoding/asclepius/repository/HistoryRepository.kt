package com.dicoding.asclepius.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.local.room.HistoryDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository private constructor(private val historyDao: HistoryDao) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllHistory(): LiveData<Result<List<HistoryEntity>>> {
        Result.Loading
        return historyDao.getAllUSer().map { historyList ->
            Result.Success(historyList)
        }
    }

    fun insert(historyEntity: HistoryEntity) {
        executorService.execute {
            try {
                historyDao.insert(historyEntity)
            } catch (e: Exception) {
                Result.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun delete() {
        executorService.execute {
            try {
                historyDao.removeAllHistory()
            } catch (e: Exception) {
                Result.Error(e.message ?: "An error occurred")
            }
        }
    }

    companion object {
        @Volatile
        private var instance: HistoryRepository? = null

        fun getInstance(
            historyDao: HistoryDao
        ): HistoryRepository =
            instance ?: synchronized(this) {
                instance ?: HistoryRepository(historyDao)
            }.also { instance = it }
    }
}