package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class CallbackQuery(
        val id: String?,
        val from: User?,
        val message: Message?,
        @JsonProperty("inline_message_id")
        val inlineMessageId: String?,
        @JsonProperty("chat_instance")
        val chatInstance: String?,
        val data: String?,
        @JsonProperty("game_short_name")
        val gameShortName: String?
)
