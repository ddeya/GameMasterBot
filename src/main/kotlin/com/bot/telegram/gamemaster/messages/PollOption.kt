package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class PollOption(
        val text: String,
        @JsonProperty("voter_count")
        val voterCount: Int
)
