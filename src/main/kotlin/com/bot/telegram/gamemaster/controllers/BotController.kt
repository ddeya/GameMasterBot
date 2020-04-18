package com.bot.telegram.gamemaster.controllers

import com.bot.telegram.gamemaster.TOKEN
import com.bot.telegram.gamemaster.core.Bot
import com.bot.telegram.gamemaster.core.SpringCoroutineScope
import com.bot.telegram.gamemaster.messages.Update
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(val bot: Bot<Update, Any>) : SpringCoroutineScope() {
    @PostMapping("/$TOKEN")
    fun onUpdate(@RequestBody update: Update) {
        launch { bot.send(update) }
    }
}