package com.moove.mooveapp.data.pref

data class UserModel(
    val userId: Int,
    val name: String,
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)
