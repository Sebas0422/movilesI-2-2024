package com.example.practico4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Person
import com.example.practico4.models.Persons
import com.example.practico4.models.Phone
import com.example.practico4.repositories.PersonRepository
import com.example.practico4.repositories.PhoneRepository

class PersonListViewModel : ViewModel() {
    private val _personList = MutableLiveData<Persons>().apply {
        value = arrayListOf()
    }
    val personList: LiveData<Persons> = _personList

    fun getPersonList(){
        PersonRepository.getPersonList(
            onSuccess = {
                _personList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }

    fun searchPerson(busqueda: String){
        PersonRepository.searchPerson(
            onSuccess = {
                _personList.value = it
            }, onError = {
                it.printStackTrace()
            },
            busqueda
        )
    }

    fun deletePerson(person: Person) {
        PersonRepository.deletePerson(person.id,
            onSuccess = {
                val currentList = _personList.value
                currentList?.remove(person)
                _personList.value = currentList
            }, onError = {
                it.printStackTrace()
            }
        )
    }
}