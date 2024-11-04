package com.example.practico4.api

import com.example.practico4.models.Phone
import com.example.practico4.models.Phones
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PhoneService {
    @GET("/api/phones")
    fun getPhoneList(): Call<Phones>

    @POST("/api/phones")
    fun createPhone(@Body phone: Phone): Call<Phone>

    @PUT("/api/phones/{idPhone}")
    fun updatePhone(@Path("idPhone") idPhone:Long, @Body phone: Phone): Call<Phone>

    @DELETE("/api/phones/{id}")
    fun deletePhone(@Path("id") id: Long): Call<Void>
}