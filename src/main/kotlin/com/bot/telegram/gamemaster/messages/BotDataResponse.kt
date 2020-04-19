package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class BotDataResponse(
        @JsonProperty("chat_id")
        val chatId: Long,
        @JsonProperty("user_id")
        val userId: Int,
//        To avoid t create new structures
        val permissions: ChatPermissions? = null,
        @JsonProperty("until_date")
        val untilDate: Int? = null
)