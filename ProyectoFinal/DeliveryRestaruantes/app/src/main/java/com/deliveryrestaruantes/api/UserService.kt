package com.deliveryrestaruantes.api

import com.deliveryrestaruantes.models.Request.LoginRequest
import com.deliveryrestaruantes.models.Request.LoginResponse
import com.deliveryrestaruantes.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @POST ("users/login")
    fun login(@Body login: LoginRequest): Call<LoginResponse>

    @POST ("users")
    fun register(@Body user: User): Call<User>

    @GET ("/me")
    fun getUser(): Call<User>
}