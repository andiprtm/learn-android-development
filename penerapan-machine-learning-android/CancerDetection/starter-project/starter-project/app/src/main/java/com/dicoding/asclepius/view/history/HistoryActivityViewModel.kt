package com.dicoding.asclepius.view.history

import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.repository.HistoryRepository

class HistoryActivityViewModel(private val historyRepository: HistoryRepository) : ViewModel() {
    fun insert(history: HistoryEntity) {
        historyRepository.insert(history)
    }

    fun getAllHistory() = historyRepository.getAllHistory()

    fun deleteAllHistory() = historyRepository.delete()
}