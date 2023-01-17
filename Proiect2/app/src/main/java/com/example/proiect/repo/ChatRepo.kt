package com.example.proiect.repo

import com.example.proiect.model.*

interface ChatRepo {
    fun createEmergencyChat() : Chat
    fun newMessage(string: String, chat:  Chat): Chat
    fun leaveChat(personId: Int, chat: Chat): Chat
    fun findChat(id: Int): Chat
    fun searchTemplate(regex: String, emergency: Boolean): List<String>
}

object ChatRepoImpl: ChatRepo {
    private val session = LoggedInUser
    private val mockData = MockData
    override fun createEmergencyChat(): Chat {
        var newPerson = Person(session.id, session.displayName,null,null,null,session.role)
        mockData.emergencyChatParticipants.add(newPerson)
        var newChat = Chat(ChatId.id, mockData.emergencyChatParticipants, mutableListOf(
            Message(newPerson,"Ajutor, sunt pierdut")
        ))
        ChatId.id += 1
        newChat.messages.addAll(ChatRoom.mockEmergencyReplies)
        ChatRoom.availableChats.add(newChat)
        return newChat
    }

    override fun newMessage(string: String, chat: Chat): Chat {
        val newPerson = Person(session.id, session.displayName,null,null,null,session.role)
        val newMessage = Message(newPerson, string)
        chat.messages.add(newMessage)
        return chat
    }

    override fun leaveChat(personId: Int, chat: Chat): Chat {
        chat.people.removeIf { it.id == personId }
        return chat
    }

    override fun findChat(id: Int): Chat {
        return ChatRoom.availableChats.first { it.id == id }
    }

    override fun searchTemplate(regex: String, emergency: Boolean): List<String> {
        return if(emergency) MockData.templateMessage.filter { it.contains(regex,true) }
        else MockData.casualTemplate.filter { it.contains(regex,true) }
    }
}