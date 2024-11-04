package com.example.practico4.api
import android.graphics.Bitmap
import com.example.practico4.models.Person
import com.example.practico4.models.Persons
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {
    @GET("/api/personas")
    fun getPersonList(): Call<Persons>

    @GET("api/search/")
    fun searchPerson(@Query("q") q: String): Call<Persons>

    @POST("api/personas")
    fun createPerson(@Body newPerson: Person): Call<Person>

    @PUT("api/personas/{id}")
    fun updatePerson(@Path("id") id: Int, person:Person): Call<Person>

    @DELETE("api/personas/{id}")
    fun deletePerson(@Path("id") id: Int): Call<Person>

    @GET ("api/personas/{id}")
    fun getPersonById(@Path("id") id: Int): Call<Person>

    @POST("api/personas/{id}/profile-picture")
    fun updateProfilePicture(@Path("id") id: Int, @Body image:Bitmap): Call<Person>
}