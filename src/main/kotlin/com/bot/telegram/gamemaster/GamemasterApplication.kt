package com.bot.telegram.gamemaster

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GameMasterApplication

fun main(args: Array<String>) {
	runApplication<GameMasterApplication>(*args)
}
