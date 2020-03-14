package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.core.router
import com.bot.telegram.gamemaster.messages.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
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
    fun router(): Router<Update, Unit> = router(applicationContext, start = true)

    @PreDestroy
    fun cancelScope() {
        coroutineContext.cancel()
    }
}

fun main(args: Array<String>) {
    runApplication<GameMasterApplication>(*args)
}
