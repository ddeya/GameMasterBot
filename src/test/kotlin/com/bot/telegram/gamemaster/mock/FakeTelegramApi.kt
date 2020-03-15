package com.bot.telegram.gamemaster.mock

import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.services.ITelegramAPI

class FakeTelegramApi : ITelegramAPI {

    override fun sendMessage(message: BotMessage): Any? {
        return message
    }

    override fun kickChatMember(message: BotDataResponse): Any? {
        return message
    }
}