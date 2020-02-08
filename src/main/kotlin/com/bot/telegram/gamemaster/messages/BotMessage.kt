package com.bot.telegram.gamemaster.messages

import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException

class BotMessage {
    private var chat_id = 0
    private var text: String? = null
    private val TELEGRAM_RESOURCE = "https://api.telegram.org/bot1084250149:AAFcGrGrmM5R3VZQkctvJ7fN4omriJP8YTw"

    fun BotMessage(chat_id: Int, text: String?) {
        this.chat_id = chat_id
        this.text = text
    }


    fun getChat_id(): Int {
        return chat_id
    }

    fun getText(): String? {
        return text
    }
}