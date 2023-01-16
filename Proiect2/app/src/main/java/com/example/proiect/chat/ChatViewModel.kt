package com.example.proiect.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proiect.model.Message
import com.example.proiect.repo.ChatRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(savedStateHandle: SavedStateHandle):ViewModel() {
    val repository= ChatRepoImpl
    var chat = repository.findChat(savedStateHandle.get<Int>("chatId") ?: 0)
    private val _viewState = MutableStateFlow(
        ChatViewState(
            messages = emptyList(),
            templates = repository.searchTemplate("",true).subList(0,3)
        )
    )
    val viewState = _viewState.asStateFlow()
    init {
        reload()
    }
    private fun reload() {
        viewModelScope.launch {

            val res = chat.messages
            _viewState.update {
                it.copy(
                    messages = res,
                )
            }
        }
    }

    fun searchTemplate(name: String) {
        val res = repository.searchTemplate(name, true)
        _viewState.update {
            it.copy(
                templates = if(res.size > 3) res.subList(0,3) else res
            )
        }
    }

    fun sendMessage(message: String) {
        chat = repository.newMessage(message, chat)
        _viewState.update {
            it.copy(
                messages = chat.messages
            )
        }
    }
}

data class ChatViewState(
    val messages: List<Message>,
    val templates: List<String>
)