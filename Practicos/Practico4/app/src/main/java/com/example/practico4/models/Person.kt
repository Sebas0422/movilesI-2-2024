package com.example.practico4.models

import android.graphics.Bitmap

typealias Persons = ArrayList<Person>

data class Person(
    val id: Int,
    val name: String,
    val last_name : String,
    val company: String,
    val address: String,
    val city: String,
    val state: String,
    val profile_picture : String,
    val phones: ArrayList<Phone>,
    val emails: ArrayList<Email>,
)