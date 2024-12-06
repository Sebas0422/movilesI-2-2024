package com.example.practico4.repositories

import android.graphics.Bitmap
import com.example.practico4.api.PersonService
import com.example.practico4.models.Person
import com.example.practico4.models.Persons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PersonRepository {
    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val service = retrofit.create(PersonService::class.java)
    
    fun getPersonList(
        onSuccess: (Persons) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getPersonList().enqueue(object : Callback<Persons> {
            override fun onResponse(call: Call<Persons>, response: Response<Persons>) {
                if (response.isSuccessful) {
                    val persons = response.body()
                    onSuccess(persons!!)
                }
            }

            override fun onFailure(call: Call<Persons>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun searchPerson(
        onSuccess: (Persons) -> Unit,
        onError: (Throwable) -> Unit,
        busqueda : String,
    ){
        service.searchPerson(busqueda).enqueue(object : Callback<Persons> {
            override fun onResponse(call: Call<Persons>, response: Response<Persons>) {
                if (response.isSuccessful) {
                    val Person = response.body()
                    onSuccess(Person!!)
                }
            }

            override fun onFailure(call: Call<Persons>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun createPerson(
        newPerson: Person,
        onSuccess: (Person) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.createPerson(newPerson).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val Person = response.body()
                    onSuccess(Person!!)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun updatePerson(
        id: Int,
        person: Person,
        onSuccess: (Person) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.updatePerson(id,person).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val Person = response.body()
                    onSuccess(Person!!)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun deletePerson(
        id:Int,
        onSuccess: (Person) -> Unit,
        onError: (Throwable) -> Unit
    ){
        service.deletePerson(id).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val Person = response.body()
                    println(Person)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    fun getPersonById(
        id:Int
    ){
        service.getPersonById(id).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val Person = response.body()
                    println(Person)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    fun updateProfilePicture(
        id:Int,
        image: Bitmap
    ){
        service.updateProfilePicture(id,image).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val Person = response.body()
                    println(Person)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }
}