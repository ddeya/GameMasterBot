package com.bot.telegram.gamemaster.core

interface Processor<T : Any, W : Any> {
    fun process(obj: T): W
}

interface ConditionalProcessor<T : Any, W : Any> : Processor<T, W> {
    fun accept(obj: T): Boolean
}

class PipeProcessor<T : Any, W : Any, E : Any>(
    private val firstProcessor: Processor<T, E>,
    private val secondProcessor: Processor<E, W>
) : ConditionalProcessor<T, W> {
    override fun process(obj: T): W = secondProcessor.process(firstProcessor.process(obj))
    override fun accept(obj: T): Boolean = true
}

operator fun <T : Any, W : Any, E : Any> Processor<T, E>.plus(other: Processor<E, W>): PipeProcessor<T, W, E> =
    PipeProcessor(this, other)

class SelectProcessor<T : Any, W : Any> : ConditionalProcessor<T, W> {

    private val processors: MutableCollection<ConditionalProcessor<T, W>> = mutableListOf()

    override fun process(obj: T): W = processors.find { it.accept(obj) }?.process(obj)
        ?: throw IllegalStateException("Unable to find a compatible processor for $obj")

    override fun accept(obj: T): Boolean = true

    operator fun ConditionalProcessor<T, W>.unaryPlus() {
        processors.add(this)
    }
}

fun <T : Any, W : Any> select(init: SelectProcessor<T, W>.() -> Unit): SelectProcessor<T, W> =
    SelectProcessor<T, W>().apply(init)