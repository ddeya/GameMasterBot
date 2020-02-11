package com.bot.telegram.gamemaster.controller

import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.Update
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject
import java.util.logging.Level
import java.util.logging.Logger

private const val API_ENDPOINT = "https://api.telegram.org/bot"
private const val ECHO_COMMAND = "/echo"
private const val TOKEN = "1084250149:AAFcGrGrmM5R3VZQkctvJ7fN4omriJP8YTw"

@RestController
class BotController {

    val logger: Logger = Logger.getLogger("[GameMaster]")

    @PostMapping("/$TOKEN")
    fun onUpdate(@RequestBody update: Update) {
        logger.log(Level.INFO, "Got update: $update")
        if (update.message != null) {
            val chatID = update.message!!.chat?.id
            val text = update.message!!.text
            when {
                text?.startsWith(ECHO_COMMAND) == true -> onEcho(chatID!!, text)
            }
        }
    }
    private fun onEcho(chatID: Int, text: String) {
        val textToSend = text.removePrefix(ECHO_COMMAND).trimStart()
        RestTemplate().postForObject<Any>("$API_ENDPOINT$TOKEN/sendMessage", BotMessage(chatID, textToSend))
    }
}