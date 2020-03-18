package com.bot.telegram.gamemaster.mock

import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.services.TelegramAPI

class FakeTelegramApi(apiUrl: String, authToken: String) : TelegramAPI(apiUrl, authToken) {
    override fun sendMessage(message: BotMessage): Any? {
        return message
    }

    override fun kickChatMember(message: BotDataResponse): Any? {
        return message
    }
}