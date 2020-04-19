package com.bot.telegram.gamemaster.services

import com.bot.telegram.gamemaster.messages.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForObject
import java.util.logging.Level
import java.util.logging.Logger

open class TelegramAPI(private val apiUrl: String, private val authToken: String) : ITelegramAPI {
    private val httpClient: RestTemplate = RestTemplate()
    val logger: Logger = Logger.getLogger("[TelegramAPI]")
    override val botUser: User by lazy {
        httpClient.getForObject<GetMeResponse>("$apiUrl$authToken/getMe").result
    }

    override fun sendMessage(message: BotMessage): Any? {
        return if (!message.text.isNullOrBlank()) {
            logRequest(message) { data ->
                httpClient.postForObject("$apiUrl$authToken/sendMessage", data)
            }
        } else null
    }

    override fun kickChatMember(message: BotDataResponse): Any? {
        return logRequest(message) { data ->
            httpClient.postForObject("$apiUrl$authToken/kickChatMember", data)
        }
    }

    override fun muteChatMember(message: BotDataResponse): Any? {
        return logRequest(message) { data ->
            httpClient.postForObject("$apiUrl$authToken/restrictChatMember", data)
        }
    }

    private fun logRequest(data: Any? = null, request: (Any?) -> Any): Any {
        logger.log(Level.INFO, "Sending Request: ${data ?: ""}")
        val response = request(data)
        logger.log(Level.INFO, "Request Response: $response")
        return response
    }
}
