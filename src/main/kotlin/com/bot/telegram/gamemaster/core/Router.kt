package com.bot.telegram.gamemaster.core

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import java.util.logging.Logger

class Router<T, W>(private val processors: List<Processor<T, W>>, var groupBy: ((T) -> Any) = { Unit }) {
    private val channels: MutableMap<Any, SendChannel<T>> = mutableMapOf()
    private val inputChannel: Channel<T> = Channel(Channel.UNLIMITED)

    private val logger: Logger = Logger.getLogger("[Router]")

    suspend fun start() = coroutineScope {
        logger.info("Starting router in Thread-${Thread.currentThread().name}")
        for (message in inputChannel) {
            val key = groupBy(message);
            if (!channels.containsKey(key)) {
                logger.info("Starting new Pipeline$key in Thread-${Thread.currentThread().name}")
                channels[key] = actor {
                    for (msg in channel) {
                        logger.info("Pipeline$key received message $msg in Thread-${Thread.currentThread().name}")
                        processors.find { it.accept(msg) }?.process(msg);
                    }
                }
            }
            logger.info("Sending message to Pipeline$key in Thread-${Thread.currentThread().name}")
            channels[key]?.send(message);
        }
    }

    suspend fun route(message: T) {
        logger.info("Got message $message in Thread-${Thread.currentThread().name}")
        inputChannel.send(message);
    }

}