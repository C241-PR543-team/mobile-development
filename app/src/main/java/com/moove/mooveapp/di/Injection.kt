package com.moove.mooveapp.di

import android.content.Context
import com.moove.mooveapp.data.UserRepository
import com.moove.mooveapp.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}