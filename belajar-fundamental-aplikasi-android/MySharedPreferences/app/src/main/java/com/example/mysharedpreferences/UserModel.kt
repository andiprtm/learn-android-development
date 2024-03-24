package com.example.mysharedpreferences

data class UserModel(
    var name: String? = null,
    var email: String? = null,
    var age: Int? = null,
    val phoneNumber: String? = null,
    val isLove: Boolean? = false
)
