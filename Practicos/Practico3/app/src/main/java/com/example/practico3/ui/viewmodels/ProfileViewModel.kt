package com.example.practico3.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3.R
import com.example.practico3.models.User

class ProfileViewModel : ViewModel(){
    private val _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>> get() = _users

    init {
        loadUsuer()
    }

    private fun loadUsuer() {
        _users.value = arrayListOf(
            User(1,"Persona 1", R.drawable.foto1 ,listOf(R.drawable.foto3, R.drawable.foto2, R.drawable.foto1, R.drawable.rottweiler3)),
            User(2,"Persona 2", R.drawable.rottweiler4 ,listOf(R.drawable.rottweiler1, R.drawable.rottweiler3))
        )
    }
}