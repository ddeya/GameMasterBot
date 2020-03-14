package com.bot.telegram.gamemaster.controllers

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.messages.Update
import kotlinx.coroutines.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PreDestroy
import kotlin.coroutines.CoroutineContext

const val API_ENDPOINT = "https://api.telegram.org/bot"
const val TOKEN = "1084250149:AAFcGrGrmM5R3VZQkctvJ7fN4omriJP8YTw"

public const val ECHO_COMMAND = "/echo"

@RestController
class BotController(val router: Router<Update, Unit>) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    @PostMapping("/$TOKEN")
    fun onUpdate(@RequestBody update: Update) {
        launch { router.route(update) }
    }

    @PreDestroy
    fun cancelScope() = coroutineContext.cancel()
}