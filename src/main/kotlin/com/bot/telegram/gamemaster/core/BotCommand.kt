package com.bot.telegram.gamemaster.core

import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Component
annotation class BotCommand
