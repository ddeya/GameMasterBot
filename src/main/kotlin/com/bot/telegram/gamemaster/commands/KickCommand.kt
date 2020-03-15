package com.bot.telegram.gamemaster.commands


import com.bot.telegram.gamemaster.core.BotCommand
import com.bot.telegram.gamemaster.core.Processor
import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.ITelegramAPI

const val KICK_COMMAND = "/kick"
const val CHAT_TYPE = "group"

@BotCommand
class KickCommand(private val telegramAPI: ITelegramAPI) : Processor<Update, String>() {

    override fun accept(obj: Update): Boolean {
        if (obj.message != null) {
            return obj.message.text?.startsWith(KICK_COMMAND) == true && (obj.message.chat.type == CHAT_TYPE) && (obj.message.forwardFrom?.id != obj.message.from?.id);
        }
        return false;
    }

    override fun process(obj: Update): String {
        if (obj.message != null) {
            val msg = obj.message;
            if (msg.forwardFrom != null) {
                //If its will be a return message
                val textToSend = "User ${msg.forwardFrom.firstName} kicked"
                telegramAPI.kickChatMember(BotDataResponse(msg.chat.id, msg.forwardFrom.id));
                telegramAPI.sendMessage(BotMessage(msg.chat.id, textToSend))
                return textToSend
            }
        }
        return "No message"
    }

    override fun priority(): Int = 1
}