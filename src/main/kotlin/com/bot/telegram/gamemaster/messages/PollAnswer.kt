package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class PollAnswer(
        @JsonProperty("poll_id")
        val pollId: String,
        val user: User,
        @JsonProperty("option_ids")
        val optionIds: Array<Int>
)
