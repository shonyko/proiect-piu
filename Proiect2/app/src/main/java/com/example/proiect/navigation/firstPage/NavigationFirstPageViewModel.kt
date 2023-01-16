package com.example.proiect.navigation.firstPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proiect.model.Location
import com.example.proiect.repo.LocationsRepoImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavigationFirstPageViewModel()
    : ViewModel() {
    private val repository = LocationsRepoImpl
    private val _viewState = MutableStateFlow(
        PeopleViewState(
            locations = emptyList(),
            isLoading = false
        )
    )
    val viewState = _viewState.asStateFlow()
    init {
        reload()
    }
    fun reload() {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    isLoading = true
                )
            }
            val res = repository.getSavedLocations()
            delay(2000)
            _viewState.update {
                it.copy(
                    locations = res,
                    isLoading = false
                )
            }
        }
    }

    fun searchByName(name: String) {
        val res = repository.searchSavedLocationByName(name)
        _viewState.update {
            it.copy(
                locations = res,
                isLoading = false
            )
        }
    }
}

data class PeopleViewState(
    val locations: List<Location>,
    val isLoading: Boolean
)