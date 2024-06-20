package com.moove.mooveapp.data.request

data class LogoutRequest(
    val userId: Int,
    val sessionToken: String
)
