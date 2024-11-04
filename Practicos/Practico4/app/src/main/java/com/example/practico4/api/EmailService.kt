package com.example.practico4.api

import com.example.practico4.models.Email
import com.example.practico4.models.Emails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmailService {
    @POST("/api/emails")
    fun  createEmail(@Body email:Email): Call<Email>

    @GET("/api/emails/{id}")
    fun getEmailById(@Path("id")id:Int): Call<Email>

    @GET("/api/emails")
    fun getEmailList(): Call<Emails>
}