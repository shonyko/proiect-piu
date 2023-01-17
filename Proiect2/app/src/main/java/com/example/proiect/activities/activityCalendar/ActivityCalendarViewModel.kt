package com.example.proiect.activities.activityCalendar

import androidx.lifecycle.ViewModel
import com.example.proiect.model.Activity
import com.example.proiect.repo.ActivityRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ActivityCalendarViewModel: ViewModel() {
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
    fun reload(year:Int, month: Int, day: Int) {
        _viewState.update {
            it.copy(
                activities = repository.getActivities(LocalDate.of(year,month,day).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString())
            )
        }
    }
}

data class ActivitiesViewState(
    val activities: List<Activity>
)