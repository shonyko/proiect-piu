package com.example.proiect.model

import androidx.fragment.app.Fragment

object MockData {
    var availablePeople: MutableList<Person> = mutableListOf(
        Person(40, "Persoana adaugabila", null, "", "07234214", Role.USER),
        Person(41, "Persoana adaugabila fara tel", null, "", null, Role.USER),
        Person(42, "Medic adaugabil", null, "", "07234214", Role.MEDIC),
        Person(43, "Medic adaugabil fara numar", null, "", null, Role.MEDIC)
    )
    var emergencyChatParticipants: MutableList<Person> = mutableListOf(
        Person(420, "Persoana 1", null, "", "07234214", Role.USER),
        Person(421, "Persoana 2", null, "", null, Role.USER),
    )
    var emergencyChatMessages: MutableList<Message> = mutableListOf(
    )

    var templateMessage: List<String> = listOf(
        "M-am pierdut",
        "Sunt ametit",
        "Am cazut",
        "Am uitat ce trebuia sa fac",
        "Nu imi gasesc rudele",
        "Sunt la spital",
        "Unde sunt?",
        "Am cazut",
        "Mi-e rau",
        "Ma doare capul"
    )

    var casualTemplate: List<String> = listOf(
        "Ce faci?",
        "Cum esti?",
        "Ce ai mai facut?",
        "Eu sunt bine",
        "Sunt la spital",
        "Vreau sa merg la o plimbare",
        "Am nevoie de mancare"
    )
}

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
            1, "user123", "user123", Role.USER, mutableListOf(
                Person(69, "Nume", null, "mock", "Phone", Role.USER)
            )
        ),
        Session(
            2,"pacient123","pacient123",Role.PACIENT,mutableListOf(
                Person(69,"Nume",null,"mock","Phone",Role.PACIENT)
            )
        ),
        Session(
            3,"medic123","medic123",Role.MEDIC,mutableListOf(
                Person(69,"Nume",null,"mock","Phone",Role.MEDIC)
            )
        )
    )
}

object ChatRoom {
    var availableChats: MutableList<Chat> = mutableListOf()
    var mockEmergencyReplies: List<Message> = listOf(
        Message(MockData.emergencyChatParticipants.get(0), "O sa vin sa te ajut"),
    Message(MockData.emergencyChatParticipants.get(1),"O sa vin si eu"),
        Message(MockData.emergencyChatParticipants.get(1), "O sa vin si eu"))
}

object AvailableActivities {
    var userActivities : MutableList<Activity> = mutableListOf(
        Notification(
            "Administreaza medicamente",
            "A sosit timpul sa iei medicamentele.",
            "2023-01-17",
            "08:00",
            1
        ),
        Notification(
            "Iesi la plimbare",
            "E timpul pentru o plimbare.",
            "2023-01-17",
            "11:00",
            60
        ),
        Notification(
            "Micul dejun",
            "Este timpul pentru micul dejun.",
            "2023-01-17",
            "09:00",
            10
        ),
        Notification(
            "Cina",
            "Este timpul pentru servirea cinei.",
            "2023-01-17",
            "19:30",
            20
        ),
        Notification(
            "Pranzul",
            "Este timpul pentru servirea pranzului.",
            "2023-01-17",
            "13:30",
            20
        ),
        Notification(
            "Vizita la medic",
            "Trebuie sa ajungi la medic.",
            "2023-01-17",
            "16:30",
            30
        ),
        Notification(
            "Vizita la medic",
            "Trebuie sa ajungi la medic.",
            "2023-01-17",
            "16:30",
            30
        ),
        Event(
            "Ziua nepotului",
            "Astazi este ziua nepotului tau.",
            "2023-01-18",
            Frequency.YEARLY
        ),
        Event(
            "Vizita saptamanala la medic",
            "Este ziua de mers la medic.",
            "2023-01-18",
            Frequency.WEEKLY
        ),
        Event(
            "Cumparaturi",
            "Este ziua de mers la cumparaturi.",
            "2023-01-20",
            Frequency.WEEKLY
        ),
        Event(
            "Apa",
            "Este momentul sa te hidratezi.",
            "2023-01-20",
            Frequency.HOURLY
        )
    )
}