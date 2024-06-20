package com.moove.mooveapp.di

import android.content.Context
import com.moove.mooveapp.data.UserRepository
import com.moove.mooveapp.data.pref.UserPreference
import com.moove.mooveapp.data.pref.dataStore
import com.moove.mooveapp.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {

        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }

        val apiConfig = ApiConfig()
        val apiService = apiConfig.getApiService(context, user.token)

        return UserRepository.getInstance(apiService, pref)
    }
}