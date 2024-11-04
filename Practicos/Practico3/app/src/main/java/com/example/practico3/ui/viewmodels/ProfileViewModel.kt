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
            User(2,"Persona 2", R.drawable.rottweiler4 ,listOf(R.drawable.rottweiler1, R.drawable.rottweiler3)),
            User(3,"Persona 3", R.drawable.persona3_1 ,listOf(R.drawable.persona3_2, R.drawable.persona3_1, R.drawable.persona3_3)),
            User(4,"Persona 4", R.drawable.persona4_1 ,listOf(R.drawable.persona4_2, R.drawable.persona4_1, R.drawable.persona4_3)),
            User(5,"Persona 5", R.drawable.persona5_1 ,listOf(R.drawable.persona5_2, R.drawable.persona5_1, R.drawable.persona5_6)),
            User(6,"Persona 6", R.drawable.persona6_1 ,listOf(R.drawable.persona6_2, R.drawable.persona6_1, R.drawable.persona6_3)),
            User(7,"Persona 7", R.drawable.persona7_1 ,listOf(R.drawable.persona7_2, R.drawable.persona7_1, R.drawable.persona7_3, R.drawable.persona7_4)),
            User(8,"Persona 8", R.drawable.persona8_1 ,listOf(R.drawable.persona8_2, R.drawable.persona8_1, R.drawable.persona8_3)),
            User(9,"Persona 9", R.drawable.persona9_1 ,listOf(R.drawable.persona9_2, R.drawable.persona9_1, R.drawable.persona9_3)),
            User(10,"Persona 10", R.drawable.persona10_1 ,listOf(R.drawable.persona10_2, R.drawable.persona10_1, R.drawable.persona10_3)),
            User(11,"Persona 11", R.drawable.persona11_1 ,listOf(R.drawable.persona11_2, R.drawable.persona11_1, R.drawable.persona11_3)),
            User(12,"Persona 12", R.drawable.persona12_1 ,listOf(R.drawable.persona12_2, R.drawable.persona12_1, R.drawable.persona12_3)),
            User(13,"Persona 13", R.drawable.persona13_1 ,listOf(R.drawable.persona13_2, R.drawable.persona13_1, R.drawable.persona13_3)),
            User(14,"Persona 14", R.drawable.persona14_1 ,listOf(R.drawable.persona14_2, R.drawable.persona14_1, R.drawable.persona14_3)),
            User(15,"Persona 15", R.drawable.persona15_1 ,listOf(R.drawable.persona15_2, R.drawable.persona15_1, R.drawable.persona15_3))
        )
    }
}