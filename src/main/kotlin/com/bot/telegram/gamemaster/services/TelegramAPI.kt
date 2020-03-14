package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.controllers.API_ENDPOINT
import com.bot.telegram.gamemaster.controllers.TOKEN
import com.bot.telegram.gamemaster.messages.BotMessage
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject
import java.util.logging.Level
import java.util.logging.Logger

@Service
class TelegramAPI(val apiUrl: String = API_ENDPOINT, val authToken: String = TOKEN) {
    private val httpClient: RestTemplate = RestTemplate()
    val logger: Logger = Logger.getLogger("[TelegramAPI]")

    fun sendMessage(message: BotMessage): Any? {
        return if (!message.text.isNullOrBlank()) {
            logRequest(message) { data ->
                httpClient.postForObject("$apiUrl$authToken/sendMessage", data)
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