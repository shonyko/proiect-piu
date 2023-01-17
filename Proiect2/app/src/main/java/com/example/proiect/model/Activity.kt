package com.example.proiect.model

import java.time.LocalDateTime

interface Activity {
    val name: String
    val description: String
    val date: String //format: yyyy-MM-dd
}

enum class Frequency {
    NEVER, HOURLY, DAILY,MONTHLY, WEEKLY, YEARLY
}
data class Notification(
    override val name: String,
    override val description: String,
    override val date: String,
    val time: String, // hh:MM
    val duration: Int
): Activity
data class Event(
    override val name: String,
    override val description: String,
    override val date: String,
    val repeating: Frequency
):Activity

data class CheckListItem(
    val actionDescription: String,
    val isChecked: Boolean
)

data class CheckList(
    override val name: String,
    override val description: String,
    override val date: String,
    val activities: MutableList<CheckListItem>
):Activity