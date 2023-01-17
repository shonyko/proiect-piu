package com.example.proiect.model

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections

data class Option (
    val name: String,
    val fragment: Fragment
)

data class ActionOption(
    val name: String,
    val description: String
)

data class MainMenuOption(
    val name: String,
    val imageId: Int,
    val colorId: Int,
    val direction: NavDirections
)
