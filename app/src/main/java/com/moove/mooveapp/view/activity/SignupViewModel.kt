package com.moove.mooveapp.view.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.moove.mooveapp.data.UserRepository
import com.moove.mooveapp.data.request.RegisterRequest
import com.moove.mooveapp.data.response.ErrorResponse
import com.moove.mooveapp.data.response.RegisterResponse
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {
    val registerResponse = MutableLiveData<RegisterResponse?>()
    fun userRegister(registerRequest: RegisterRequest) {
        val rawJson = "{\"name\":\"${registerRequest.name}\"," +
                "\"phone\":\"${registerRequest.phone}\"," +
                "\"birthday\":\"${registerRequest.birthday}\"," +
                "\"email\":\"${registerRequest.email}\"," +
                "\"password\":\"${registerRequest.password}\"}"

        viewModelScope.launch{
            val response = userRepository.userRegister(rawJson)
            if (response?.isSuccessful == true) {
                registerResponse.postValue(response.body())
            } else {
                val gson = Gson()
                val errorStream = response?.errorBody()?.charStream()
                if (errorStream != null) {
                    val errorResponse = gson.fromJson(errorStream, ErrorResponse::class.java)
                    registerResponse.postValue(RegisterResponse(errorResponse.message, null, errorResponse.status))
                } else {
                    registerResponse.postValue(RegisterResponse("An error occurred", null, 0.toString()))
                }
            }
        }
    }
}