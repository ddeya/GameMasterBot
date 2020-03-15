package com.bot.telegram.gamemaster.messages


data class Chat(
    val id: Long,
    val bot: Boolean,
    val firstName: String? = null,
    val lastName: String? = null,
    val type: String? = null
)
