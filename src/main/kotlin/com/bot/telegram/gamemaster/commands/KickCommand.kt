package com.bot.telegram.gamemaster.commands


import com.bot.telegram.gamemaster.core.ConditionalProcessor
import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.CHATTYPE
import com.bot.telegram.gamemaster.services.ITelegramAPI

const val KICK_COMMAND = "/kick"
val compatibleTypes = arrayOf(CHATTYPE.GROUP.toString(), CHATTYPE.SUPERGROUP.toString())

class KickCommand(private val telegramAPI: ITelegramAPI) : ConditionalProcessor<Update, Any> {
    override fun accept(obj: Update): Boolean {
        if (obj.message != null) {
            return obj.message.text?.startsWith(KICK_COMMAND) == true
                    && obj.message.replyToMessage?.from?.id != obj.message.from?.id
                    && compatibleTypes.any { types -> obj.message.chat.type == types }
        }
        return false
    }

    override fun process(obj: Update): Any {
        if (obj.message != null) {
            val msg = obj.message
            val textToSend: String
            if (msg.replyToMessage?.from != null) {
                if (msg.replyToMessage.from.id == telegramAPI.botUser.id) {
                    textToSend = "User @${msg.from?.username}, nice try"
                    telegramAPI.sendMessage(BotMessage(msg.chat.id, textToSend))
                } else {
                    textToSend = "User ${msg.replyToMessage.from.firstName} kicked"
                    telegramAPI.kickChatMember(BotDataResponse(msg.chat.id, msg.replyToMessage.from.id))
                    telegramAPI.sendMessage(BotMessage(msg.chat.id, textToSend))
                }
                return textToSend
            }
        }
        return ""
    }
}