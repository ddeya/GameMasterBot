package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage

interface ITelegramAPI {
    fun sendMessage(message: BotMessage): Any?
    fun kickChatMember(message: BotDataResponse): Any?
}