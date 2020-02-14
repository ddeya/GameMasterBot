package com.bot.telegram.gamemaster.messages

class PollAnswer(
        val poll_id: String,
        val user: User,
        val option_ids: Array<Int>
)
