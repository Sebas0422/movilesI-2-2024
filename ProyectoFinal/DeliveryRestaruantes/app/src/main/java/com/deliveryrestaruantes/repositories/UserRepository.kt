package com.deliveryrestaruantes.repositories

import com.deliveryrestaruantes.api.UserService
import com.deliveryrestaruantes.models.Request.LoginRequest
import com.deliveryrestaruantes.models.Request.LoginResponse
import com.deliveryrestaruantes.models.User
import com.deliveryrestaruantes.network.PublicRetrofitRepository
import com.deliveryrestaruantes.security.TokenManager

object UserRepository {
    fun login(
        login: LoginRequest,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val service = PublicRetrofitRepository.createService(UserService::class.java)
        service.login(login).enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse>,
                response: retrofit2.Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val token = response.body()?.access_token
                    TokenManager.setToken(token!!)
                    onSuccess(token)
                }
            }

            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun register(
        user: User,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val service = PublicRetrofitRepository.createService(UserService::class.java)
        service.register(user).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: retrofit2.Call<User>, response: retrofit2.Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    onSuccess(user!!)
                }
            }

            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                onError(t)
            }
        })
    }
}