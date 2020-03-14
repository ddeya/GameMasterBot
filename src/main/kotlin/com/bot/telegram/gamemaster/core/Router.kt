package com.bot.telegram.gamemaster.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.context.ApplicationContext
import java.util.logging.Logger

class Router<T, W>(
    private val processors: MutableList<Processor<T, W>>,
    var groupBy: ((T) -> Any),
    private val inputChannel: ReceiveChannel<T>,
    private val outputChannel: SendChannel<W>?
) {
    private val channels: MutableMap<Any, SendChannel<T>> = mutableMapOf()


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
                        if (outputChannel != null && result != null) {
                            outputChannel.send(result)
                        }
                    }
                }
            }
            logger.info("Sending message to Pipeline-$key")
            channels[key]?.send(message);
        }
    }
}


fun <T, W> CoroutineScope.router(
    applicationContext: ApplicationContext,
    groupBy: (T) -> Any = { Unit },
    inputChannel: SendChannel<T>,
    outputChannel: ReceiveChannel<W>? = null,
    start: Boolean = false
): Router<T, W> {
    val processors = mutableListOf<Processor<T, W>>()
    applicationContext.getBeansWithAnnotation(BotCommand::class.java).forEach {
        processors += it.value as Processor<T, W>
    }
    return Router(processors, groupBy, inputChannel as Channel, outputChannel as Channel?).apply {
        if (start) {
            launch { start() }
        }
    }
}