package com.dicoding.asclepius.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity ORDER BY id ASC")
    fun getAllUSer(): LiveData<List<HistoryEntity>>

    @Query("DELETE FROM HistoryEntity")
    fun removeAllHistory()
}