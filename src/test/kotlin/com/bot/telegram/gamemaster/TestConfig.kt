package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.core.SpringCoroutineScope
import com.bot.telegram.gamemaster.core.twoWayRouter
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.mock.FakeTelegramApi
import com.bot.telegram.gamemaster.services.ITelegramAPI
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class TestConfig(val applicationContext: ApplicationContext) : SpringCoroutineScope() {

    @Primary
    @Bean
    fun fakeTelegramApi(): ITelegramAPI = FakeTelegramApi(API_ENDPOINT, TOKEN)

    @Primary
    @Bean(destroyMethod = "destroy")
    fun testRouter(): Router<Update, Any> = twoWayRouter(applicationContext, start = true)
}