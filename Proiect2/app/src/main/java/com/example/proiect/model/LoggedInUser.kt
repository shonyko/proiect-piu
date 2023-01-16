package com.example.proiect.model

import com.example.proiect.navigation.firstPage.NavigationFirstPageFragment
import com.example.proiect.people.contacts.PeopleFragment
import com.example.proiect.quiz.QuizFragment

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
object LoggedInUser {
    var displayName: String = ""
    var role: Role = Role.USER
    var contactPeople: MutableList<Person> = mutableListOf(
        Person(69, "Nume", null, "mock", "Phone", Role.USER)
    )
    var savedLocations: MutableList<Location> = mutableListOf(
        Location(
            1, "Magazin", "Magazinul pentru mancare",
            "Str. Republicii nr 101"
        ),
        Location(
            1,
            "Căminul numărul 7",
            "Acasă",
            "Strada Observatorului, Cluj-Napoca 400000"
        ),
        Location(
            2,
            "Spitalul Județean de Urgență Cluj-Napoca",
            "Spital pentru control medical",
            "Str. Clinicilor 3-5, Cluj-Napoca 400347"
        ),
        Location(
            3,
            "Lidl",
            "Magazin alimentar pentru mâncare",
            "Strada Republicii 109, Cluj-Napoca 400489"
        ),
        Location(
            4,
            "Auchan",
            "Hypermarket pentru nevoi extra",
            "Strada Alexandru Vaida Voevod 53B, Cluj-Napoca 400436"
        ),
        Location(
            5,
            "Îngrijitor",
            "Casa îngrijitorului",
            "Strada Vasile Lupu nr.35, Cluj-Napoca 400423"
        )
    )

    fun login(user: String) {
        val user = AvailableSession.possibleUsers.first { it.name.equals(user, true) }
        displayName = user.name
        role = user.role
        contactPeople = user.contactPeople
    }

    fun menuOptions(): List<Option> {
        when (role) {
            Role.USER -> {
                return listOf(
                    Option("Contacte", PeopleFragment()),
                    Option("Quizzes", QuizFragment())
                )
            }
            Role.PACIENT -> {
                return listOf(
                    Option("Contacte", PeopleFragment()),
                    Option("Navigare", NavigationFirstPageFragment()),
                    Option("Activitati", PeopleFragment()),
                    Option("Quizzes", QuizFragment())
                )
            }
            Role.MEDIC -> {
                return listOf(
                    Option("Contacte", PeopleFragment())
                )
            }
        }
    }
}