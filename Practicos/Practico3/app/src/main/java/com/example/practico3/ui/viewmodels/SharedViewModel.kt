package com.example.practico3.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3.models.User

class SharedViewModel : ViewModel() {
    private val _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>> get() = _users

    fun addLikedUser(user: User) {
        val currentList = _users.value ?: ArrayList()
        currentList.add(user)
        _users.value = currentList
    }
    fun getLikedUsers(): LiveData<ArrayList<User>> = _users

}
