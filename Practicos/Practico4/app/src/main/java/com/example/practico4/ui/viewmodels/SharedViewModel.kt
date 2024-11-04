package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Person

class SharedViewModel : ViewModel() {
    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    fun setPerson(person: Person) {
        _person.value = person
    }

    fun getPersona(): LiveData<Person> = _person
}