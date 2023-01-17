package com.example.proiect.repo

import com.example.proiect.model.Location
import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.MockData

interface LocationsRepo {
    fun getSavedLocations(): List<Location>
    fun addLocation(location: Location): List<Location>
    fun searchByName(name: String): List<Location>
    fun searchSavedLocationByName(name: String): List<Location>
    fun getSavedLocation(id:Int): Location
    fun getLocation(id:Int): Location
}
object LocationsRepoImpl: LocationsRepo {
    private var session = LoggedInUser
    private var mockData = MockData
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

    override fun getSavedLocation(id: Int): Location =
        session.savedLocations.first{ it.id == id }

    override fun getLocation(id: Int): Location =
        session.savedLocations.first{ it.id == id }
}