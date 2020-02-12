package com.bot.telegram.gamemaster.messages


data class From(
    val id: Int,
    val bot: Boolean,
    val firstName: String?,
    val lastName: String?,
    val type: String?,
    val languageCode: String?
)
