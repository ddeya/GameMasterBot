package com.bot.telegram.gamemaster.commands


import com.bot.telegram.gamemaster.core.BotCommand
import com.bot.telegram.gamemaster.core.Processor
import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.CHATTYPE
import com.bot.telegram.gamemaster.services.ITelegramAPI

const val KICK_COMMAND = "/kick"
val compatibleTypes = arrayOf(CHATTYPE.GROUP, CHATTYPE.SUPERGROUP)

@BotCommand
class KickCommand(private val telegramAPI: ITelegramAPI) : Processor<Update, String>() {

    override fun accept(obj: Update): Boolean {
        if (obj.message != null) {
            return obj.message.text?.startsWith(KICK_COMMAND) == true
                    && (obj.message.replyToMessage?.from?.id != obj.message.from?.id
                    && obj.message.replyToMessage?.from?.id != telegramAPI.getMe()?.id
                    && compatibleTypes.any { types -> obj.message.chat.type == types.value }
                    && obj.message.replyToMessage?.from?.id != obj.message.from?.id
                    )
        }
        return false;
    }

    override fun process(obj: Update): String {
        if (obj.message != null) {
            val msg = obj.message;
            if (msg.replyToMessage?.from != null) {
                val textToSend = "User ${msg.replyToMessage.from.firstName} kicked"
                telegramAPI.kickChatMember(BotDataResponse(msg.chat.id, msg.replyToMessage.from.id));
                telegramAPI.sendMessage(BotMessage(msg.chat.id, textToSend))
                return textToSend
            }
        }
        return "No message"
    }

    override fun priority(): Int = 1
}