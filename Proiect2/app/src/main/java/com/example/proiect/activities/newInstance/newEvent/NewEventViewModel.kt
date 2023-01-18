package com.example.proiect.activities.newInstance.newEvent

import androidx.lifecycle.ViewModel
import com.example.proiect.repo.UserRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewEventViewModel: ViewModel() {

    private val initialState = NewActivityViewState()
    private val _viewState = MutableStateFlow(initialState)
    private val userRepo = UserRepoImpl()
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

    fun getSelectedOption() {

    }

    fun onCreate() {
        val title = _viewState.value.title.trim()
        val description = _viewState.value.title.trim()
        var time = _viewState.value.time
        if(time.length == 4) time = "0"+time

        val date = LocalDate.parse(_viewState.value.date,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        val titleError = if(title.isBlank()) InputErrorType.Empty else
            null
        val dateError = if(date.isBefore(LocalDate.now())) InputErrorType.BadDate else null
        if(titleError != null || dateError != null) {
            _viewState.update { state ->
                state.copy(
                    action = ActivityAction.ShowInputErrors(
                        titleError = titleError,
                        dateError = dateError
                    )
                )
            }
        }
    }
}

data class NewActivityViewState(
    val title: String = "",
    val description: String = "",
    val time: String = "00:00",
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    val action: ActivityAction? = null
)


sealed class ActivityAction {

    data class ShowInputErrors(
        val titleError: InputErrorType?,
        val dateError: InputErrorType?
    ): ActivityAction()

    object NewActivity: ActivityAction()
}
sealed class InputErrorType {
    object Empty: InputErrorType()
    object BadDate: InputErrorType()
}