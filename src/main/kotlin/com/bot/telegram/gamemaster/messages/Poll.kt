package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class Poll(
        val id: String,
        val question: String,
        val options: Array<PollOption>,
        @JsonProperty("total_voter_count")
        val totalVoterCount: Int,
        @JsonProperty("is_closed")
        val isClosed: Boolean,
        @JsonProperty("is_anonymous")
        val isAnonymous: Boolean,
        val type: String,
        @JsonProperty("allows_multiple_answers")
        val allowsMultipleAnswers: Boolean,
        @JsonProperty("correct_option_id")
        val correctOptionId: Int?
)