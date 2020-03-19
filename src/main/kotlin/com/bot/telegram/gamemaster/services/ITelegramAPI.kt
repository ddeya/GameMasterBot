package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.User

enum class CHATTYPE {
    GROUP,
    SUPERGROUP;

    override fun toString(): String {
        return name.toLowerCase()
    }
}

interface ITelegramAPI {
    val botUser: User
    fun sendMessage(message: BotMessage): Any?
    fun kickChatMember(message: BotDataResponse): Any?
}