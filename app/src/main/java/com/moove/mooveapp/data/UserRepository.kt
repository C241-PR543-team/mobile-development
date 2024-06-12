package com.moove.mooveapp.data

import com.moove.mooveapp.data.response.LoginResponse
import com.moove.mooveapp.data.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.awaitResponse


class UserRepository private constructor(
    private val apiService: ApiService) {
    suspend fun userLogin(rawJson: String): Response<LoginResponse>? {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = rawJson.toRequestBody(jsonMediaType)
        return withContext(Dispatchers.IO) {
            apiService.userLogin(requestBody).awaitResponse()
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}