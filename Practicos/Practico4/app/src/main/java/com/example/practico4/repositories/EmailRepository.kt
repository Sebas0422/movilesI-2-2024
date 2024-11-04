package com.example.practico4.repositories

import com.example.practico4.api.EmailService
import com.example.practico4.models.Email
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EmailRepository {
    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val service = retrofit.create(EmailService::class.java)

    fun createEmail(
        email: Email
    ){
        service.createEmail(email).enqueue(object : Callback<Email> {
            override fun onResponse(call: Call<Email>, response: Response<Email>) {
                if (response.isSuccessful){
                    val email = response.body()
                    println(email)
                }
            }

            override fun onFailure(call: Call<Email>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }
}