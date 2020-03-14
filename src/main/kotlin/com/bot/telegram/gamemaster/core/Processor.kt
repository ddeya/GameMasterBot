package com.bot.telegram.gamemaster.core

abstract class Processor<T, W> : Comparable<Processor<T, W>> {
    abstract fun accept(obj: T): Boolean
    abstract fun process(obj: T): W
    abstract fun priority(): Int

    override fun compareTo(other: Processor<T, W>): Int = priority() - other.priority()
}