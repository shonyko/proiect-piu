package com.example.proiect.activities.newInstance.newActivity

import androidx.lifecycle.ViewModel
import com.example.proiect.model.Notification
import com.example.proiect.repo.ActivityRepoImpl
import com.example.proiect.repo.UserRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewActivityViewModel: ViewModel() {

    private val initialState = NewActivityViewState()
    private val _viewState = MutableStateFlow(initialState)
    private val userRepo = UserRepoImpl()
    private val actRepo = ActivityRepoImpl
    val viewState = _viewState.asStateFlow()

    fun onTitleChanged(newTitle: String) {
        _viewState.update { state ->
            state.copy(
                title = newTitle,
                action = null
            )
        }
    }

    fun onDescriptionChanged(newDescription: String) {
        _viewState.update { state ->
            state.copy(
                description = newDescription,
                action = null
            )
        }
    }

    fun onDateChanged(newDate: String) {
        _viewState.update { state ->
            state.copy(
                date = newDate,
                action = null
            )
        }
    }

    fun onTimeChanged(newTime: String) {
        _viewState.update { state ->
            state.copy(
                time = newTime,
                action = null
            )
        }
    }

    fun onDurationChanged(newDuration: Int) {
        _viewState.update { state ->
            state.copy(
                duration = newDuration,
                action = null
            )
        }
    }

    fun onCreate() {
        val title = _viewState.value.title.trim()
        val description = _viewState.value.title.trim()
        val date = LocalDateTime.parse(_viewState.value.date+" "+_viewState.value.time,
            DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))
        val duration = _viewState.value.duration

        val titleError = if(title.isBlank()) InputErrorType.Empty else
            null
        val dateError = if(date.isBefore(LocalDateTime.now())) InputErrorType.BadDate else null
        val durationError = if(duration < 5) InputErrorType.BadQuantity else null

        if(titleError != null || dateError != null || durationError != null) {
            println("Errors")
            _viewState.update { state ->
                state.copy(
                    action = ActivityAction.ShowInputErrors(
                        titleError = titleError,
                        dateError = dateError,
                        durationError = durationError
                    )
                )
            }
        }
        else addActivity()
    }

    fun addActivity() {
        var activity = Notification(
            viewState.value.title.trim(),
            viewState.value.description.trim(),
            viewState.value.date,
            viewState.value.time,
            viewState.value.duration
        )
        actRepo.addActivity(activity)
        _viewState.update { state ->
            state.copy(
                action = ActivityAction.NewActivity
            )
        }
    }
}

data class NewActivityViewState(
    val title: String = "",
    val description: String = "",
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val time: String = "00:00",
    val duration: Int = 5,
    val action: ActivityAction? = null
)


sealed class ActivityAction {

    data class ShowInputErrors(
        val titleError: InputErrorType?,
        val dateError: InputErrorType?,
        val durationError: InputErrorType?
    ): ActivityAction()

    object NewActivity: ActivityAction()
}
sealed class InputErrorType {
    object Empty: InputErrorType()
    object BadDate: InputErrorType()
    object BadQuantity: InputErrorType()
}