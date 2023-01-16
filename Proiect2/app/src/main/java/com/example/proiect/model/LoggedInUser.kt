package com.example.proiect.model

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.proiect.activities.todaysActivities.TodaysActivitiesFragment
import com.example.proiect.navigation.firstPage.NavigationFirstPageFragment
import com.example.proiect.people.contacts.PeopleFragment

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
object LoggedInUser {
    var id = 0
    var displayName: String = ""
    var role: Role = Role.USER
    var contactPeople: MutableList<Person> = mutableListOf(
    Person(69,"Nume",null,"mock","Phone",Role.USER)
    )
    var savedLocations: MutableList<Location> = mutableListOf(
        Location(0,"Magazin","Magazinul pentru mancare",
            "Str. Republicii nr 101",12),
        Location(1,
            "Căminul numărul 7",
            "Acasă",
            "Strada Observatorului, Cluj-Napoca 400000",40
        ),
        Location(2,
            "Spitalul Județean de Urgență Cluj-Napoca",
            "Spital pentru control medical",
            "Str. Clinicilor 3-5, Cluj-Napoca 400347",23
        ),
        Location(3,
            "Lidl",
            "Magazin alimentar pentru mâncare",
            "Strada Republicii 109, Cluj-Napoca 400489",43
        ),
        Location(4,
            "Auchan",
            "Hypermarket pentru nevoi extra",
            "Strada Alexandru Vaida Voevod 53B, Cluj-Napoca 400436",41
        ),
        Location(5,
            "Îngrijitor",
            "Casa îngrijitorului",
            "Strada Vasile Lupu nr.35, Cluj-Napoca 400423",32
        )
    )

    fun login(user: String) {
        val user = AvailableSession.possibleUsers.first { it.name.equals(user, true) }
        id = user.id
        displayName = user.name
        role = user.role
        contactPeople = user.contactPeople
    }
    fun menuOptions(): List<Option> {
        when(role){
            Role.USER -> {
                return listOf(
                    Option("Contacte", PeopleFragment())
                )
            }
            Role.PACIENT -> {
                return listOf(
                    Option("Contacte", PeopleFragment()),
                    Option("Navigare", NavigationFirstPageFragment()),
                    Option("Activitati", TodaysActivitiesFragment())
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