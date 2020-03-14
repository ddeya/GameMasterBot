package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.core.router
import com.bot.telegram.gamemaster.messages.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import javax.annotation.PreDestroy
import kotlin.coroutines.CoroutineContext

@SpringBootApplication
class GameMasterApplication(val applicationContext: ApplicationContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job()

    @Bean
    fun routerInput(): SendChannel<Update> = Channel(Channel.UNLIMITED)

    @Bean
    fun router(
        inputChannel: SendChannel<Update>
    ): Router<Update, Unit> =
        router(
            applicationContext,
            inputChannel = inputChannel,
            start = true
        )

    @PreDestroy
    fun cancelScope() {
        coroutineContext.cancel()
    }
}

fun main(args: Array<String>) {
    runApplication<GameMasterApplication>(*args)
}
