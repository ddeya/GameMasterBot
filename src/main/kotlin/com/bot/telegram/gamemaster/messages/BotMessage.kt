package com.bot.telegram.gamemaster.messages

class BotMessage(private var chat_id: Int, private var text: String?) {
    fun getChat_id(): Int {
        return chat_id
    }

    fun getText(): String? {
        return text
    }
}