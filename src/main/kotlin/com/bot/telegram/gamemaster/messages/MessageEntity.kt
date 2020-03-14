package com.bot.telegram.gamemaster.messages

data class MessageEntity(
        val type: String,
        val offset: Int,
        val length: Int,
        val url: String?,
        val user: User?,
        val language: String?
)