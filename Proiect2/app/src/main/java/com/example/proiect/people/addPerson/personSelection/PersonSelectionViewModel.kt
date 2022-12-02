package com.example.proiect.people.addPerson.personSelection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proiect.model.Person
import com.example.proiect.people.PeopleViewModel
import com.example.proiect.people.PeopleViewState
import com.example.repo.PersonRepo
import com.example.repo.PersonRepoImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonSelectionViewModel(savedStateHandle: SavedStateHandle)
    : ViewModel() {
    private val repository = PersonRepoImpl
    val showPhone: Boolean = savedStateHandle.get<Boolean>("fromContacts") ?: false
    private val _viewState = MutableStateFlow(
        PeopleViewState(
            characters = emptyList(),
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
            val chars = repository.getAvailablePeople(showPhone)
            delay(2000)
            _viewState.update {
                it.copy(
                    characters = chars,
                    isLoading = false
                )
            }
        }
    }

    fun searchByName(name: String) {
        val people = repository.searchAllPeople(name, showPhone)
        _viewState.update {
            it.copy(
                characters = people,
                isLoading = false
            )
        }
    }

    fun addPerson(id: Int) {
        val person = repository.getAvailablePerson(id)
        repository.addPerson(person);
    }

    fun getPersonName(id: Int): Person =
        repository.getAvailablePerson(id)
}
