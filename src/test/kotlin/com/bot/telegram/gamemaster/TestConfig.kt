package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Bot
import com.bot.telegram.gamemaster.core.Processor
import com.bot.telegram.gamemaster.core.SpringCoroutineScope
import com.bot.telegram.gamemaster.core.eventBasedBot
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.mock.FakeTelegramApi
import com.bot.telegram.gamemaster.services.ITelegramAPI
import kotlinx.coroutines.channels.Channel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class TestConfig() : SpringCoroutineScope() {

    @Primary
    @Bean
    fun fakeTelegramApi(): ITelegramAPI = FakeTelegramApi(API_ENDPOINT, TOKEN)

    @Primary
    @Bean(destroyMethod = "destroy")
    fun testRouter(processor: Processor<Update, Any>): Bot<Update, Any> = eventBasedBot(
        processor, outputChannel = Channel(Channel.BUFFERED), start = true
    )
}