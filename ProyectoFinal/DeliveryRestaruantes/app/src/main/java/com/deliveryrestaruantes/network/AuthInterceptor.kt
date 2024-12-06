package com.deliveryrestaruantes.network

import com.deliveryrestaruantes.security.TokenManager
import okhttp3.Interceptor

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        val token = TokenManager.getToken()

        val authenticatedRequest = if (token != null) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(authenticatedRequest)
    }
}