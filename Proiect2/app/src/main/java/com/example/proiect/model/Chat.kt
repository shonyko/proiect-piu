package com.example.proiect.model

data class Chat (
    var id: Int,
    var people: MutableList<Person>,
    var messages: MutableList<Message>
    )
object ChatId {
    var id: Int = 0
}
data class Message(
    var author: Person,
    var message: String
)