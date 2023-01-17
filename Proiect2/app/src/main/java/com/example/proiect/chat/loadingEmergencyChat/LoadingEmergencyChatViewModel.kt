package com.example.proiect.chat.loadingEmergencyChat

import androidx.lifecycle.ViewModel
import com.example.proiect.model.Chat
import com.example.proiect.repo.ChatRepoImpl

class LoadingEmergencyChatViewModel:ViewModel() {
    private val repository = ChatRepoImpl
    fun createChat() : Chat {
        return repository.createEmergencyChat()
    }
}