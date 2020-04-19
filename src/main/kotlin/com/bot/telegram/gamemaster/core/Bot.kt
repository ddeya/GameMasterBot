package com.bot.telegram.gamemaster.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.logging.Logger

interface Bot<T, W> {
    suspend fun start()
    suspend fun send(message: T)
    fun getOutputChannel(): ReceiveChannel<W>?
    fun destroy(): Boolean
}

open class EventBasedBot<T : Any, W : Any>(
    private val processor: Processor<T, W>,
    open var groupBy: ((T) -> Any),
    private val inputChannel: Channel<T> = Channel(Channel.BUFFERED),
    private val outputChannel: SendChannel<W>?
) : Bot<T, W> {
    private val channels: MutableMap<Any, SendChannel<T>> = mutableMapOf()

    private val logger: Logger = Logger.getLogger("[EventBasedBot]")

    @ObsoleteCoroutinesApi
    override suspend fun start(): Unit = coroutineScope {
        logger.info("Starting Bot")
        for (message in inputChannel) {
            val key = groupBy(message)
            if (!channels.containsKey(key)) {
                logger.info("Starting new IsolationThread-$key")
                channels[key] = actor {
                    for (msg in channel) {
                        logger.info("IsolationThread-$key received message $msg")
                        try {
                            val result = processor.process(msg)
                            outputChannel?.send(result)
                        }catch(_:Exception){}
                    }
                }
            }
            logger.info("Sending message to IsolationThread-$key")
            channels[key]?.send(message)
        }
        outputChannel?.close()
        channels.forEach { (_, v) -> v.close() }
    }

    override suspend fun send(message: T) {
        inputChannel.send(message)
    }

    override fun destroy(): Boolean = inputChannel.close()

    override fun getOutputChannel(): ReceiveChannel<W>? = outputChannel as Channel<W>?
}

fun <T : Any, W : Any> CoroutineScope.eventBasedBot(
    processor: Processor<T, W>,
    groupBy: (T) -> Any = { Unit },
    outputChannel: SendChannel<W>? = null,
    start: Boolean = false
): Bot<T, W> {
    return EventBasedBot(processor, groupBy, outputChannel = outputChannel).apply {
        if (start) {
            launch { start() }
        }
    }
}