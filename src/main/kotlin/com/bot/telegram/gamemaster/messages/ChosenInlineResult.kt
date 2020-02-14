package com.bot.telegram.gamemaster.messages

class ChosenInlineResult(
        val result_id: String?,
        val from: User?,
        val location: Location?,
        val inline_message_id: String?,
        val query: String?
)
