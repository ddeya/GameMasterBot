package com.bot.telegram.gamemaster.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.context.ApplicationContext
import java.util.*
import java.util.logging.Logger

class Router<T, W>(
    private val processors: PriorityQueue<Processor<T, W>>,
    var groupBy: ((T) -> Any)
) {
    private val channels: MutableMap<Any, SendChannel<T>> = mutableMapOf()
    private val inputChannel: Channel<T> = Channel(Channel.BUFFERED)
    private val outputChannel: SendChannel<W> = Channel(Channel.BUFFERED)

    private val logger: Logger = Logger.getLogger("[Router]")

    suspend fun start() = coroutineScope {
        logger.info("Starting router")
        for (message in inputChannel) {
            val key = groupBy(message);
            if (!channels.containsKey(key)) {
                logger.info("Starting new Pipeline-$key")
                channels[key] = actor {
                    for (msg in channel) {
                        logger.info("Pipeline-$key received message $msg")
                        val result = processors.find { it.accept(msg) }?.process(msg)
                        if (result != null) {
                            outputChannel.send(result)
                        }
                    }
                }
            }
            logger.info("Sending message to Pipeline-$key")
            channels[key]?.send(message);
        }
    }

    suspend fun send(msg: T) {
        inputChannel.send(msg)
    }

    fun getOutput(): ReceiveChannel<W> = outputChannel as Channel<W>
}


fun <T, W> CoroutineScope.router(
    applicationContext: ApplicationContext,
    groupBy: (T) -> Any = { Unit },
    start: Boolean = false
): Router<T, W> {
    val processors = PriorityQueue<Processor<T, W>>()
    applicationContext.getBeansWithAnnotation(BotCommand::class.java).forEach {
        processors += it.value as Processor<T, W>
    }
    return Router(processors, groupBy).apply {
        if (start) {
            launch { start() }
        }
    }
}