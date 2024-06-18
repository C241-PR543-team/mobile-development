package com.moove.mooveapp.data.request

data class RegisterRequest(
    val name: String,
    val phone: String,
    val birthday: String,
    val email: String,
    val password: String
)
