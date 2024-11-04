package com.example.practico4.models

typealias Phones = ArrayList<Phone>

data class Phone(
    var id: Long? = null,
    var number: String,
    val persona_id: Int,
    var label: String,
){
    private lateinit var person : Person
}