package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.messages.BotMessage
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject
import java.util.logging.Level
import java.util.logging.Logger

class TelegramAPI(private val apiUrl: String, private val authToken: String) {
    private val httpClient: RestTemplate = RestTemplate()
    val logger: Logger = Logger.getLogger("[TelegramAPI]")

    fun sendMessage(message: BotMessage): Any = logRequest(message) { data ->
        httpClient.postForObject("$apiUrl$authToken/sendMessage", data)
    }
}

private fun TelegramAPI.logRequest(data: Any? = null, request: (Any?) -> Any): Any {
    logger.log(Level.INFO, "Sending Request: ${data ?: ""}")
    val response = request(data)
    logger.log(Level.INFO, "Request Response: $response")
    return response
}