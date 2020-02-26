package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class ChosenInlineResult(
        @JsonProperty("result_id")
        val resultId: String?,
        val from: User?,
        val location: Location?,
        @JsonProperty("inline_message_id")
        val inlineMessageId: String?,
        val query: String?
)
