package com.deliveryrestaruantes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deliveryrestaruantes.models.Request.LoginRequest
import com.deliveryrestaruantes.models.User
import com.deliveryrestaruantes.repositories.UserRepository
import com.deliveryrestaruantes.security.TokenManager

class UserViewModel: ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun login(email: String, password: String) {
        println("Llego al login del viewModel")
        println("Email: $email")
        println("Password: $password")
        val login = LoginRequest(email, password)
        UserRepository.login(login,
            onSuccess = {
            },
            onError = {
            }
        )
    }

    fun register (user: User){
        UserRepository.register(user,
            onSuccess = {
            },
            onError = {
            }
        )
    }
}