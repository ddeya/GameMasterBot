package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.messages.BotDataResponse
import com.bot.telegram.gamemaster.messages.BotMessage
import com.bot.telegram.gamemaster.messages.User
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject
import java.util.logging.Level
import java.util.logging.Logger

class TelegramAPI(private val apiUrl: String, private val authToken: String) : ITelegramAPI {
    private val httpClient: RestTemplate = RestTemplate()
    val logger: Logger = Logger.getLogger("[TelegramAPI]")
    val lazyChatBotId: User by lazy {
        httpClient.getForObject("$apiUrl$authToken/getMe", User::class.java)
    }


    override fun sendMessage(message: BotMessage): Any? {
        return if (!message.text.isNullOrBlank()) {
            logRequest(message) { data ->
                httpClient.postForObject("$apiUrl$authToken/sendMessage", data)
            }
        } else null
    }

    override fun kickChatMember(message: BotDataResponse): Any? {
        return if (message.userId != null) {
            logRequest(message) { data ->
                httpClient.postForObject("$apiUrl$authToken/kickChatMember", data)
            }
        } else null
    }

    private fun TelegramAPI.logRequest(data: Any? = null, request: (Any?) -> Any): Any {
        logger.log(Level.INFO, "Sending Request: ${data ?: ""}")
        val response = request(data)
        logger.log(Level.INFO, "Request Response: $response")
        return response
    }
}