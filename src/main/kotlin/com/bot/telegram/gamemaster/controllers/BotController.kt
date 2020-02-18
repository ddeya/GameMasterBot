package com.bot.telegram.gamemaster.controllers

import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.TelegramAPI
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Level
import java.util.logging.Logger

const val API_ENDPOINT = "https://api.telegram.org/bot"
const val TOKEN = "1084250149:AAFcGrGrmM5R3VZQkctvJ7fN4omriJP8YTw"

private const val ECHO_COMMAND = "/echo"

@RestController
class BotController(val telegramAPI: TelegramAPI) {

    private val logger: Logger = Logger.getLogger("[GameMaster]")

    @PostMapping("/$TOKEN")
    fun onUpdate(@RequestBody update: Update) {
        logger.log(Level.INFO, "Got update: $update")
        if (update.message != null) {
            val chatID = update.message.chat.id
            val text = update.message.text
            when {
                text?.startsWith(ECHO_COMMAND) == true -> onEcho(chatID, text)
            }
        }
    }

    private fun onEcho(chatID: Long, text: String) {
        val textToSend = text.removePrefix(ECHO_COMMAND).trimStart()
        telegramAPI.sendMessage(BotMessage(chatID, textToSend))
    }
}