package com.example.proiect.model

import com.example.proiect.R
import com.example.proiect.mainMenu.MainMenuFragmentDirections



/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
object LoggedInUser {
    var id = 0
    var displayName: String = ""
    var role: Role = Role.USER
    var contactPeople: MutableList<Person> = mutableListOf(
        Person(69, "Nume", null, "mock", "Phone", Role.USER)
    )

    var savedLocations: MutableList<Location> = mutableListOf(
        Location(
            0, "Magazin", "Magazinul pentru mancare",
            "Str. Republicii nr 101", 12
        ),
        Location(
            1,
            "Căminul numărul 7",
            "Acasă",
            "Strada Observatorului, Cluj-Napoca 400000", 40
        ),
        Location(
            2,
            "Spitalul Județean de Urgență Cluj-Napoca",
            "Spital pentru control medical",
            "Str. Clinicilor 3-5, Cluj-Napoca 400347", 23
        ),
        Location(
            3,
            "Lidl",
            "Magazin alimentar pentru mâncare",
            "Strada Republicii 109, Cluj-Napoca 400489", 43
        ),
        Location(
            4,
            "Auchan",
            "Hypermarket pentru nevoi extra",
            "Strada Alexandru Vaida Voevod 53B, Cluj-Napoca 400436", 41
        ),
        Location(
            5,
            "Îngrijitor",
            "Casa îngrijitorului",
            "Strada Vasile Lupu nr.35, Cluj-Napoca 400423", 32
        )
    )

    fun login(user: String) {
        val user = AvailableSession.possibleUsers.first { it.name.equals(user, true) }
        id = user.id
        displayName = user.name
        role = user.role
        contactPeople = user.contactPeople
    }

    fun menuOptions(): List<MainMenuOption> {
        when(role){
            Role.USER -> {
                return listOf(
                    MainMenuOption("Contacte", R.drawable.ic_baseline_people_alt_24, R.color.mainColor,
                    MainMenuFragmentDirections.goToContacts()),
                    MainMenuOption("Quizuri", R.drawable.ic_baseline_quiz_24, R.color.mainColor,
                    MainMenuFragmentDirections.goToContacts())
                )
            }
            Role.PACIENT -> {
                return listOf(
                    MainMenuOption("Calendar", R.drawable.ic_baseline_calendar_month_24, R.color.mainColor,
                        MainMenuFragmentDirections.goToCalendar()),
                    MainMenuOption("Navigare", R.drawable.ic_baseline_map_24, R.color.mainColor,
                    MainMenuFragmentDirections.goToLocations()),
                    MainMenuOption("Contacte", R.drawable.ic_baseline_people_alt_24, R.color.mainColor,
                        MainMenuFragmentDirections.goToContacts()),
                    MainMenuOption("Quizzes", R.drawable.ic_baseline_quiz_24, R.color.mainColor,
                    MainMenuFragmentDirections.goToQuiz()),
                    MainMenuOption("Galerie", R.drawable.ic_baseline_image_24, R.color.mainColor,
                            MainMenuFragmentDirections.goToStatistics())
                )

            }
            Role.MEDIC -> {
                return listOf(
                MainMenuOption("Pacienti",R.drawable.ic_baseline_insert_chart_24, R.color.mainColor,
                    MainMenuFragmentDirections.goToPatients()))

            }
        }
    }
}