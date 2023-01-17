package com.example.proiect.model

data class Question(
    val text: String,
    val imageUrl: String?,
    val answers: List<String>,
    val correctAnswer: String
)
