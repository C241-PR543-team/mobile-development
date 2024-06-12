package com.moove.mooveapp.view.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.moove.mooveapp.data.UserRepository
import com.moove.mooveapp.data.request.LoginRequest
import com.moove.mooveapp.data.response.ErrorResponse
import com.moove.mooveapp.data.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val loginResponse = MutableLiveData<LoginResponse?>()
    fun userLogin(loginRequest: LoginRequest) {
        val rawJson = "{\"email\":\"${loginRequest.email}\",\"password\":\"${loginRequest.password}\"}"
        viewModelScope.launch {
            val response = userRepository.userLogin(rawJson)
            if (response?.isSuccessful ?: false) {
                loginResponse.postValue(response?.body())
            } else {
                val gson = Gson()
                val errorResponse = gson.fromJson(response?.errorBody()?.charStream(), ErrorResponse::class.java)
                loginResponse.postValue(LoginResponse(errorResponse.message, null, errorResponse.status))
            }
        }
    }
}