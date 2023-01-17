package com.example.proiect.activities.todaysActivities

import androidx.lifecycle.ViewModel
import com.example.proiect.chat.ChatViewState
import com.example.proiect.model.Activity
import com.example.proiect.model.Message
import com.example.proiect.repo.ActivityRepo
import com.example.proiect.repo.ActivityRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodaysActivitiesViewModel: ViewModel() {
    val repository = ActivityRepoImpl
    private val _viewState = MutableStateFlow(
        ActivitiesViewState(
            activities = emptyList()
        )
    )

    val viewState = _viewState.asStateFlow()
    fun reload() {
        _viewState.update {
            it.copy(
                activities = repository.getActivities(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString())
            )
        }
    }
}

data class ActivitiesViewState(
    val activities: List<Activity>
)