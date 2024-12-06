package com.deliveryrestaruantes.security

object TokenManager {
    private var accessToken: String? = null

    fun setToken(token: String) {
        accessToken = token
    }

    fun getToken(): String? {
        return accessToken
    }
}