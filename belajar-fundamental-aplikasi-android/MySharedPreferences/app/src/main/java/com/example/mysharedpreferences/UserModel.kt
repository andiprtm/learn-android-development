package com.example.mysharedpreferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var name: String? = null,
    var email: String? = null,
    var age: Int? = null,
    val phoneNumber: String? = null,
    val isLove: Boolean? = false
): Parcelable
