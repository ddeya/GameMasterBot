package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class BotMessage(
        @JsonProperty("chat_id")
        val chatId: Long,
        val text: String?
)