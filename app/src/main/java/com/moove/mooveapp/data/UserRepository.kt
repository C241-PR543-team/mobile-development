package com.moove.mooveapp.data

import com.moove.mooveapp.data.pref.UserModel
import com.moove.mooveapp.data.pref.UserPreference
import com.moove.mooveapp.data.response.LoginResponse
import com.moove.mooveapp.data.response.RegisterResponse
import com.moove.mooveapp.data.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.awaitResponse


class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    private val coroutineScope: CoroutineScope) {

    suspend fun userLogin(rawJson: String): Response<LoginResponse>? {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = rawJson.toRequestBody(jsonMediaType)
        return withContext(Dispatchers.IO) {
            apiService.userLogin(requestBody).awaitResponse()
        }
    }

    suspend fun userRegister(rawJson: String): Response<RegisterResponse>? {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = rawJson.toRequestBody(jsonMediaType)
        return withContext(Dispatchers.IO) {
            apiService.userRegister(requestBody).awaitResponse()
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference, CoroutineScope(Dispatchers.IO))
            }.also { instance = it }
    }
}