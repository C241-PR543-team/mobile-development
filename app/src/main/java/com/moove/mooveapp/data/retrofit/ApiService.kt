package com.moove.mooveapp.data.retrofit

import com.moove.mooveapp.data.response.LoginResponse
import com.moove.mooveapp.data.response.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


// TODO: TEST LOGIN API pakai ViewModel Repository?
interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun userLogin(@Body loginRequest: RequestBody): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun userRegister(@Body registerRequest: RegisterResponse): Call<RegisterResponse>
}