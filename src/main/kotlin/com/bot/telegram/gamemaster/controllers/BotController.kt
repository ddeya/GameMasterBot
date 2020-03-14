package com.bot.telegram.gamemaster.controllers

import com.bot.telegram.gamemaster.TOKEN
import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.core.SpringCoroutineScope
import com.bot.telegram.gamemaster.messages.Update
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(val router: Router<Update, Unit>) : SpringCoroutineScope() {
    @PostMapping("/$TOKEN")
    fun onUpdate(@RequestBody update: Update) {
        launch { router.send(update) }
    }
}