package com.example.repo

import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.MockData
import com.example.proiect.model.Person

interface PersonRepo {
    fun getContactPeople(): List<Person>
    fun getAvailablePeople(contactsOnly: Boolean): List<Person>
    fun addPerson(person: Person): List<Person>
    fun searchPerson(name: String): List<Person>
    fun getContactPerson(id: Int): Person
    fun getAvailablePerson(id: Int): Person
    fun searchAllPeople(name: String, contactsOnly: Boolean): List<Person>
    fun removeContact(person: Person): List<Person>
}
object PersonRepoImpl : PersonRepo {
    private var session = LoggedInUser
    private var mockData = MockData
    override fun getContactPeople(): List<Person> = session.contactPeople
    override fun getAvailablePeople(contactsOnly: Boolean): List<Person> =
        mockData.availablePeople.filter { !contactsOnly || (contactsOnly && it.phone!=null) }

    override fun addPerson(person: Person): List<Person> {
        session.contactPeople.add(person)
        mockData.availablePeople.removeIf { it.id != person.id }
        return session.contactPeople
    }

    override fun searchPerson(name: String): List<Person> =
        session.contactPeople.filter { it.name.contains(name,true) }

    override fun getContactPerson(id: Int): Person =
        session.contactPeople.first { it.id == id }

    override fun getAvailablePerson(id: Int): Person =
        mockData.availablePeople.first { it.id == id }

    override fun searchAllPeople(name: String, contactsOnly: Boolean): List<Person> =
        mockData.availablePeople.filter { it.name.contains(name,true) &&
                (!contactsOnly || (contactsOnly && it.phone!=null)) }

    override fun removeContact(person: Person): List<Person> {
        mockData.availablePeople.add(person)
        session.contactPeople.removeIf { it.id==person.id }
        return mockData.availablePeople
    }
}