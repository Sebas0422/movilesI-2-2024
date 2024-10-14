package com.example.ejemplofotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    // LiveData para observar los usuarios
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        // Inicializamos los usuarios con datos predefinidos
        loadUsers()
    }

    private fun loadUsers() {
        // Puedes cargar los usuarios desde una base de datos o API en un caso real
        _users.value = listOf(
            User("Persona 1", listOf(R.drawable.foto1, R.drawable.foto2)),
            User("Persona 2", listOf(R.drawable.foto3, R.drawable.foto4))
        )
    }
}
