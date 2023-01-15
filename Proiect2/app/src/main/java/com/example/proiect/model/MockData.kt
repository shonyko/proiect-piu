package com.example.proiect.model

import androidx.fragment.app.Fragment
import com.example.proiect.people.contacts.PeopleFragment

object MockData {
    var availablePeople: MutableList<Person> = mutableListOf(
        Person(40,"Persoana adaugabila",null,"","07234214",Role.USER),
        Person(41,"Persoana adaugabila fara tel",null,"",null,Role.USER),
        Person(42,"Medic adaugabil",null,"","07234214",Role.MEDIC),
        Person(43,"Medic adaugabil fara numar",null,"",null,Role.MEDIC)
    )

}
data class Option (
    val name: String,
    val fragment: Fragment
)

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
object AvailableSession {
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