package com.example.proiect.model

import com.example.proiect.people.contacts.PeopleFragment

object MockData {
    var availablePeople: MutableList<Person> = mutableListOf(
        Person(40,"Persoana adaugabila",null,"","07234214",Role.USER),
        Person(41,"Persoana adaugabila fara tel",null,"",null,Role.USER),
        Person(42,"Medic adaugabil",null,"","07234214",Role.MEDIC),
        Person(43,"Medic adaugabil fara numar",null,"",null,Role.MEDIC)
    )

}