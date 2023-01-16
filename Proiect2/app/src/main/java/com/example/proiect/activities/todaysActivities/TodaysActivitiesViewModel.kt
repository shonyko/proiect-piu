package com.example.proiect.activities.todaysActivities

import androidx.lifecycle.ViewModel
import com.example.proiect.chat.ChatViewState
import com.example.proiect.model.Activity
import com.example.proiect.model.Message
import kotlinx.coroutines.flow.MutableStateFlow

class TodaysActivitiesViewModel: ViewModel() {
    private val _viewState = MutableStateFlow(
        ActivitiesViewState(
            activities = emptyList()
        )
    )
    fun reload() {

    }
}

data class ActivitiesViewState(
    val activities: List<Activity>
)