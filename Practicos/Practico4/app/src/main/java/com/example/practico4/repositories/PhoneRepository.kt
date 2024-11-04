package com.example.practico4.repositories

import android.util.Log
import com.example.practico4.api.PhoneService
import com.example.practico4.models.Phone
import com.example.practico4.models.Phones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PhoneRepository {
    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val service = retrofit.create(PhoneService::class.java)
    fun getPhoneList(

    ){
        service.getPhoneList().enqueue(object : Callback<Phones> {
            override fun onResponse(call: Call<Phones>, response: Response<Phones>) {
                if (response.isSuccessful){
                    val phone = response.body()
                    println(phone)
                }
            }

            override fun onFailure(call: Call<Phones>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    fun createPhone(
        phone: Phone,
        onSuccess: (Phone) -> Unit,
        onError: (Throwable) -> Unit
    ){
        service.createPhone(phone).enqueue(object : Callback<Phone> {
            override fun onResponse(call: Call<Phone>, response: Response<Phone>) {
                Log.d("PhoneRepository", "onResponse code: ${response.code()}, body: ${response.body()}")
                if (response.isSuccessful){
                    val newPhone = response.body()
                    onSuccess(newPhone!!)
                }
            }

            override fun onFailure(call: Call<Phone>, t: Throwable) {
                println("Error: ${t.message}")
                Log.d("PhoneRepository", "onFailure: ${t.message}")
                onError(t)
            }
        })
    }

    fun updatePhone(
        idPhone: Long,
        phone: Phone,
        onSuccess: (Phone) -> Unit,
        onError: (Throwable) -> Unit
    ){
        service.updatePhone(idPhone, phone).enqueue(object : Callback<Phone> {
            override fun onResponse(call: Call<Phone>, response: Response<Phone>) {
                Log.d("PhoneRepository", "onResponse code: ${response.code()}, body: ${response.body()}")
                if (response.isSuccessful){
                    val phone = response.body()
                    onSuccess(phone!!)
                }
            }

            override fun onFailure(call: Call<Phone>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun deletePhone(
        id: Long,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ){
        service.deletePhone(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("PhoneRepository", "onResponse code: ${response.code()}")
                if (response.isSuccessful){
                    onSuccess()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}