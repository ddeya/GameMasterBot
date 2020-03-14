package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Message(
    @JsonProperty("message_id")
    val messageId: Int,
    val from: User?,
    val chat: Chat,
    val date: Date?,
    val text: String?,
    @JsonProperty("reply_to_message")
    val replyToMessage: Message?
)