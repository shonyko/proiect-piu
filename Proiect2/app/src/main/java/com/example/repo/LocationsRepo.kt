package com.example.repo

import com.example.proiect.model.Location
import com.example.proiect.model.LoggedInUser

interface LocationsRepo {
    fun getSavedLocations(): List<Location>
    fun addLocation(location: Location): List<Location>
    fun searchByName(name: String): List<Location>
    fun searchSavedLocationByName(name: String): List<Location>
}
object LocationsRepoImpl: LocationsRepo {
    private var session = LoggedInUser
    override fun getSavedLocations(): List<Location> =
    session.savedLocations


    override fun addLocation(location: Location): List<Location> {
        session.savedLocations.add(location)
        return session.savedLocations
    }

    override fun searchByName(name: String): List<Location> =
        session.savedLocations.filter { it.name.contains(name, true) || it.address.contains(name, true) }

    override fun searchSavedLocationByName(name: String): List<Location> =
        session.savedLocations.filter { it.name.contains(name, true) || it.address.contains(name, true) }


}