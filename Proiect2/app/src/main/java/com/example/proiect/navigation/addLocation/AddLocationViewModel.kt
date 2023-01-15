package com.example.proiect.navigation.addLocation

import androidx.lifecycle.ViewModel
import com.example.proiect.model.Location
import com.example.repo.LocationsRepo
import com.example.repo.LocationsRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddLocationViewModel(
    private val locationsRepo: LocationsRepo = LocationsRepoImpl
) : ViewModel() {

    private val REQUIRED_ERR = "Câmp obligatoriu"

    private val _state = MutableStateFlow(AddLocationState())
    val viewState = _state.asStateFlow()

    fun addLocation(name: String?, description: String?, address: String?) {
        val nameError = if (name.isNullOrEmpty()) REQUIRED_ERR else null
        val descriptionError = if (description.isNullOrEmpty()) REQUIRED_ERR else null
        val addressError = if (address.isNullOrEmpty()) REQUIRED_ERR else null

        val valid = listOf(nameError, descriptionError, addressError).all {
            it == null
        }

        if (valid) {
            val id = locationsRepo.getSavedLocations().size
            val location = Location(
                id,
                name!!,
                description!!,
                address!!
            )
            locationsRepo.addLocation(location)
        }

        val status: SubmitStatus = if (valid) SubmitStatus.Valid else SubmitStatus.Invalid

        _state.update {
            it.copy(
                status = status,
                nameError = nameError,
                descriptionError = descriptionError,
                addressError = addressError
            )
        }
    }
}

data class AddLocationState(
    val status: SubmitStatus = SubmitStatus.NotSubmitted,
    val nameError: String? = null,
    val descriptionError: String? = null,
    val addressError: String? = null,
)

sealed class SubmitStatus {
    object NotSubmitted : SubmitStatus()
    object Valid : SubmitStatus()
    object Invalid : SubmitStatus()
}