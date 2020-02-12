package com.bot.telegram.gamemaster.messages


data class Update(
    val updateId: Int,
    val message: Message?,
    val editedMessage: Message?,
    val channelPost: Message?,
    val editedChannelPost: Message?
)
