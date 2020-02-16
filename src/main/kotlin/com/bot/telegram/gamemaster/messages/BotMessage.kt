package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class BotMessage(
        @JsonProperty("chat_id")
        val chatId: Long,
        val text: String?
)