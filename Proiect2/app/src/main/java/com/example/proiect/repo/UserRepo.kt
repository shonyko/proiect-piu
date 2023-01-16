package com.example.proiect.repo

import com.example.proiect.model.AvailableSession

interface UserRepo {
    fun checkUserName(username: String) : Boolean
    fun checkCredentials(username: String, password: String): Boolean
}
class UserRepoImpl: UserRepo {
    private val availableUsers = AvailableSession
    override fun checkUserName(username: String): Boolean =
        availableUsers.possibleUsers.firstOrNull { it.name.equals(username,true) } != null;


    override fun checkCredentials(username: String, password: String) : Boolean =
        availableUsers.possibleUsers.firstOrNull { it.name.equals(username,true)&& it.name == password } != null;
}