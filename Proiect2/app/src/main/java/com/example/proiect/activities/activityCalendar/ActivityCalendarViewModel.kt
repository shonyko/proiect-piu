package com.example.proiect.activities.activityCalendar

import androidx.lifecycle.ViewModel
import com.example.proiect.model.Activity
import kotlinx.coroutines.flow.MutableStateFlow

class ActivityCalendarViewModel: ViewModel() {
    private val _viewState = MutableStateFlow(
        ActivitiesViewState(
            activities = emptyList()
        )
    )
    fun reload() {

    }

    fun getActivitiesFromDay(day:String) {

    }
}

data class ActivitiesViewState(
    val activities: List<Activity>
)