package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.commands.DefaultCommand
import com.bot.telegram.gamemaster.commands.EchoCommand
import com.bot.telegram.gamemaster.commands.KickCommand
import com.bot.telegram.gamemaster.core.*
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.services.ITelegramAPI
import com.bot.telegram.gamemaster.services.TelegramAPI
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

const val API_ENDPOINT = "https://api.telegram.org/bot"
const val TOKEN = "1084250149:AAFcGrGrmM5R3VZQkctvJ7fN4omriJP8YTw"

@SpringBootApplication
class GameMasterApplication : SpringCoroutineScope() {

    @Bean(destroyMethod = "destroy")
    fun router(processor: Processor<Update, Any>): Bot<Update, Any> = eventBasedBot(processor, start = true)

    @Bean
    fun mainProcessor(api: ITelegramAPI): Processor<Update, Any> = select {
        +EchoCommand(api)
        +KickCommand(api)
        +DefaultCommand(api)
    }

    @Bean
    fun telegramApi(): ITelegramAPI = TelegramAPI(API_ENDPOINT, TOKEN)
}

fun main(args: Array<String>) {
    runApplication<GameMasterApplication>(*args)
}
