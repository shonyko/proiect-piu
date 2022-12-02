package com.example.proiect.model

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.proiect.people.contacts.PeopleFragment

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
object LoggedInUser {
    var displayName: String = ""
    var role: Role = Role.USER
    var contactPeople: MutableList<Person> = mutableListOf(
    Person(69,"Nume",null,"mock","Phone",Role.USER)
    )

    fun login(user: String) {
        val user = AvailableSession.possibleUsers.first { it.name.equals(user, true) }
        displayName = user.name
        role = user.role
        contactPeople = user.contactPeople
    }
    fun menuOptions(): List<Option> {
        var options:MutableList<Option> = mutableListOf()
        when(role){
            Role.USER -> {
                return listOf(
                    Option("Contacte", PeopleFragment())
                )
            }
            Role.PACIENT -> {
                return listOf(
                    Option("Contacte", PeopleFragment()),
                    Option("Navigare", PeopleFragment()),
                    Option("Activitati", PeopleFragment())
                )
            }
            Role.MEDIC -> {
                return listOf()
            }
        }
    }
}

data class Option (
    val name: String,
    val fragment: Fragment)

data class ActionOption(
    val name: String,
    val description: String
)

data class Session(
    val id: Int,
    val name: String,
    val password: String,
    val role: Role,
    var contactPeople: MutableList<Person>
)
object AvailableSession{
    var possibleUsers: MutableList<Session> = mutableListOf(
        Session(
            0,"user123","user123",Role.USER,mutableListOf(
                Person(69,"Nume",null,"mock","Phone",Role.USER)
            )
        ),
        Session(
            0,"pacient123","pacient123",Role.PACIENT,mutableListOf(
                Person(69,"Nume",null,"mock","Phone",Role.USER)
            )
        ),
        Session(
            0,"medic123","medic123",Role.MEDIC,mutableListOf(
                Person(69,"Nume",null,"mock","Phone",Role.USER)
            )
        )
    )
}