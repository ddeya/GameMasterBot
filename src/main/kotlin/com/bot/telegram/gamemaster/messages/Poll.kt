package com.bot.telegram.gamemaster.messages

class Poll(
        val id: String,
        val question: String,
        val options: Array<PollOption>,
        val total_voter_count: Int,
        val is_closed: Boolean,
        val is_anonymous: Boolean,
        val type: String,
        val allows_multiple_answers: Boolean,
        val correct_option_id: Int?
)