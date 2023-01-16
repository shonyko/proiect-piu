package com.example.proiect.navigation.navigateTo.confirmNavigation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.proiect.characterDetails.PersonDetailsViewState
import com.example.proiect.model.Location
import com.example.proiect.repo.LocationsRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConfirmNavigationViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val repository = LocationsRepoImpl
    private val _viewState = MutableStateFlow(ConfirmLocationViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val id = savedStateHandle.get<Int>("locationId")
        val fromSaved = savedStateHandle.get<Boolean>("fromSaved")
        id?.let {
            val res = if(fromSaved == true) repository.getSavedLocation(id) else
                repository.getLocation(id)
            _viewState.update { state ->
                state.copy(
                    location = res
                )
            }
        }
    }

}

data class ConfirmLocationViewState(
    val location: Location? = null
)
