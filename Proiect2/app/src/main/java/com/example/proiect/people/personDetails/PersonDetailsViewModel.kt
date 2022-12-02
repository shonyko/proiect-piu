package com.example.proiect.characterDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.proiect.model.Person
import com.example.repo.PersonRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PersonDetailsViewModel(savedStateHandle: SavedStateHandle) :ViewModel() {
    private val repository = PersonRepoImpl

    private val _viewState = MutableStateFlow(PersonDetailsViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val id = savedStateHandle.get<Int>("characterId")
        id?.let {
            val character = repository.getContactPerson(id)
            _viewState.update { state ->
                state.copy(
                    character = character
                )
            }
        }
    }

    fun deleteCharacter() {
        repository.removeContact(viewState.value.character!!)
    }
}

data class PersonDetailsViewState(
    val character: Person? = null
)
