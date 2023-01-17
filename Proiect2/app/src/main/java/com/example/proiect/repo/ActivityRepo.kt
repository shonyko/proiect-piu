package com.example.proiect.repo

import com.example.proiect.model.Activity
import com.example.proiect.model.AvailableActivities
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface ActivityRepo {
    fun getActivities(fromDate: String): List<Activity>
    fun addActivity(activity: Activity): List<Activity>
}
object ActivityRepoImpl: ActivityRepo {
    override fun getActivities(fromDate: String): List<Activity> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return AvailableActivities.userActivities.filter {
            LocalDate.parse(it.date, formatter).equals(LocalDate.parse(fromDate))
        }
    }

    override fun addActivity(activity: Activity): List<Activity> {
        AvailableActivities.userActivities.add(activity)
        return AvailableActivities.userActivities
    }
}