package com.example.practico4.ui.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico4.models.Phone
import com.example.practico4.repositories.PhoneRepository

class PhoneViewModel:ViewModel() {
    private val _phone = MutableLiveData<ArrayList<Phone>>()
    val phone: LiveData<ArrayList<Phone>> get() = _phone

    fun addPhone(phone: Phone) {
        val currentList = _phone.value ?: ArrayList()
        currentList.add(phone)
        _phone.value = currentList
    }

    fun getPhones(): LiveData<ArrayList<Phone>> = _phone

    private val _createdPhone = MutableLiveData<Phone?>()
    val createdPhone: LiveData<Phone?> get() = _createdPhone

    @SuppressLint("NullSafeMutableLiveData")
    fun createPhone(index: Int) {
        PhoneRepository.createPhone(_phone.value!![index],
            onSuccess = {
                val currentList = _phone.value
                if (currentList != null) {
                    currentList[index] = it
                    _phone.value = currentList
                }
                _createdPhone.value = it
                Log.d("PhoneViewModel", "Phone created: ${it.id}")
            },
            onError = {
                it.printStackTrace()
                Log.d("PhoneViewModel", "Error creating phone")
                _createdPhone.value = null // o puedes manejar el error de otra manera
            }
        )
    }

    fun updatePhone(index: Int, phone: Phone) {
        PhoneRepository.updatePhone(_phone.value!![index].id!!, phone,
            onSuccess = {
            _phone.value!![index] = it
        }, onError = {
            it.printStackTrace()
        })
    }

    fun deletePhone(phone: Phone) {
        PhoneRepository.deletePhone(phone.id!!,
            onSuccess = {
                val currentList = _phone.value
                currentList?.remove(phone)
                _phone.value = currentList!!
            }, onError = {
                it.printStackTrace()
            }
        )
    }
}