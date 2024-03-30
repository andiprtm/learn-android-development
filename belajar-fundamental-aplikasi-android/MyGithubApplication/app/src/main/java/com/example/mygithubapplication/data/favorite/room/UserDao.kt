package com.example.mygithubapplication.data.favorite.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygithubapplication.data.favorite.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Query("DELETE FROM UserEntity WHERE UserEntity.id = :id")
    fun removeFavorite(id: Int)

    @Query("SELECT * FROM UserEntity ORDER BY username ASC")
    fun getAllUser(): LiveData<List<UserEntity>>
}