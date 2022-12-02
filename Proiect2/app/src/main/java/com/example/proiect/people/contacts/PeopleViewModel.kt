package com.example.proiect.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proiect.model.Person
import com.example.repo.PersonRepo
import com.example.repo.PersonRepoImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class PeopleViewModel(private val repository: PersonRepo = PersonRepoImpl)
    : ViewModel() {
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
            val chars = repository.getContactPeople()
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
        val people = repository.searchPerson(name)
        _viewState.update {
            it.copy(
                characters = people,
                isLoading = false
            )
        }
    }
}

data class PeopleViewState(
    val characters: List<Person>,
    val isLoading: Boolean
)