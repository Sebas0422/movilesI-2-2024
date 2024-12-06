package com.deliveryrestaruantes.models

data class User(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String? = null,
    val role: Int,
    val profile: Profile? = null
)

data class Profile(
    val id: Int,
    val role: Int
)
