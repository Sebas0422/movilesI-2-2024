package com.example.practico4.models

typealias Emails = ArrayList<Email>
data class Email(
    val id: Long,
    val email: String,
    val persona_id: Long,
    val label: String,
)