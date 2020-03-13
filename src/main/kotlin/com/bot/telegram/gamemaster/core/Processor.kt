package com.bot.telegram.gamemaster.core

interface Processor<in T, out W> {
    fun accept(obj: T): Boolean
    fun process(obj: T): W
}