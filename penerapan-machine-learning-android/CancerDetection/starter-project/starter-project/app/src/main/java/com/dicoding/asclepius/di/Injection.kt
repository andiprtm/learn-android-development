package com.dicoding.asclepius.di

import android.content.Context
import com.dicoding.asclepius.data.local.room.HistoryRoomDatabase
import com.dicoding.asclepius.repository.HistoryRepository

object Injection {
    fun provideHistoryRepository(context: Context): HistoryRepository {
        val database = HistoryRoomDatabase.getDatabase(context)
        val dao = database.historyDao()
        return HistoryRepository.getInstance(dao)
    }
}