package com.example.mygithubapplication.data.favorite.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "username")
    var login: String? = null,

    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = null,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,
) : Parcelable