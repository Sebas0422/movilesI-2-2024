package com.deliveryrestaruantes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PublicRetrofitRepository {
    private const val BASE_URL = "https://proyectodelivery.jmacboy.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}