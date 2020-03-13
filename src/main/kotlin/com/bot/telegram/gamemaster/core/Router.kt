package com.bot.telegram.gamemaster.core

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope

class Router<T, W>(private val processors: List<Processor<T, W>>, var groupBy: ((T) -> Any) = { Unit }) {
    private val channels: MutableMap<Any, SendChannel<T>> = mutableMapOf();
    private val inputChannel: Channel<T> = Channel(Channel.UNLIMITED);

    suspend fun start() = coroutineScope {
        for (message in inputChannel) {
            val key = groupBy(message);
            if (!channels.containsKey(key)) {
                channels[key] = actor<T> {
                    for (msg in channel) {
                        processors.find { it.accept(msg) }?.process(msg);
                    }
                }
            }
            channels[key]?.send(message);
        }
    }

    suspend fun route(message: T) {
        inputChannel.send(message);
    }

}