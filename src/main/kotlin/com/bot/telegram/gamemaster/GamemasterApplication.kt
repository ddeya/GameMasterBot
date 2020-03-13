package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.processors.EchoCommand
import com.bot.telegram.gamemaster.services.TelegramAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.annotation.PreDestroy
import kotlin.coroutines.CoroutineContext

@SpringBootApplication
class GameMasterApplication : CoroutineScope {

	override val coroutineContext: CoroutineContext
		get() = Job()

	@Bean
	fun createRouter(telegramAPI: TelegramAPI) = Router(listOf(EchoCommand(telegramAPI))).apply {
		launch { start() }
	}

	@PreDestroy
	fun cancelScope() {
		coroutineContext.cancel()
	}
}

fun main(args: Array<String>) {
	runApplication<GameMasterApplication>(*args)
}
