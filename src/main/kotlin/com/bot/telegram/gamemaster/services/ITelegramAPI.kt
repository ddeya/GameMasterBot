package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.User

enum class CHATTYPE(val value: String) {
    GROUP("group"),
    SUPERGROUP("supergroup")
}

interface ITelegramAPI {
    fun sendMessage(message: BotMessage): Any?
    fun kickChatMember(message: BotDataResponse): Any?