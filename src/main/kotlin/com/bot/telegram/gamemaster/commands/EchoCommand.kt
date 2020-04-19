package com.bot.telegram.gamemaster.commands

import com.bot.telegram.gamemaster.core.ConditionalProcessor
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.ITelegramAPI

const val ECHO_COMMAND = "/echo"

class EchoCommand(private val telegramAPI: ITelegramAPI) : ConditionalProcessor<Update, Any> {
    override fun accept(obj: Update): Boolean = obj.message?.text?.startsWith(ECHO_COMMAND) == true

    override fun process(obj: Update) {
        if (obj.message != null) {
            val msg = obj.message
            if (msg.text != null) {
                val textToSend = msg.text.removePrefix(ECHO_COMMAND).trimStart()
                telegramAPI.sendMessage(BotMessage(msg.chat.id, textToSend))
            }
        }
    }


}