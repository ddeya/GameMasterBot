package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.core.SpringCoroutineScope
import com.bot.telegram.gamemaster.core.router
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.TelegramAPI
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

const val API_ENDPOINT = "https://api.telegram.org/bot"
const val TOKEN = "1084250149:AAFcGrGrmM5R3VZQkctvJ7fN4omriJP8YTw"

@SpringBootApplication
class GameMasterApplication(val applicationContext: ApplicationContext) : SpringCoroutineScope() {
    @Bean
    fun router(): Router<Update, Unit> = router(applicationContext, start = true)

    @Bean
    fun telegramApi(): TelegramAPI = TelegramAPI(API_ENDPOINT, TOKEN)
}

fun main(args: Array<String>) {
    runApplication<GameMasterApplication>(*args)
}
