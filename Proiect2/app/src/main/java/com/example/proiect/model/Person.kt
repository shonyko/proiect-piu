package com.example.proiect.model

data class Person(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String?,
    val phone: String?,
    val role: Role)

data class PersonDescription(
    val personDescribed: Int,
    val describedBy: Int,
    val text: String
)

fun resolveDescription() {

}