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

interface Router<T, W> {
    suspend fun start()
    suspend fun send(message: T)
    fun getOutputChannel(): ReceiveChannel<W>?
    fun destroy(): Boolean
}

open class TwoWayRouter<T : Any, W : Any>(
    private val processors: Collection<Processor<T, W>>,
    open var groupBy: ((T) -> Any),
    private val inputChannel: Channel<T> = Channel(Channel.BUFFERED),
    private val outputChannel: SendChannel<W>? = Channel(Channel.BUFFERED)
) : Router<T, W> {
    private val channels: MutableMap<Any, SendChannel<T>> = mutableMapOf()

    private val logger: Logger = Logger.getLogger("[Router]")

    override suspend fun start() = coroutineScope {
        logger.info("Starting router")
        for (message in inputChannel) {
            val key = groupBy(message)
            if (!channels.containsKey(key)) {
                logger.info("Starting new Pipeline-$key")
                channels[key] = actor {
                    for (msg in channel) {
                        logger.info("Pipeline-$key received message $msg")
                        val result = processors.find {
                            it.accept(msg)
                        }?.run {
                            logger.info("Pipeline-$key processing message $msg using ${this.javaClass.name}")
                            process(msg)
                        }
                        if (result != null) {
                            outputChannel?.send(result)
                        }
                    }
                }
            }
            logger.info("Sending message to Pipeline-$key")
            channels[key]?.send(message)
        }
        channels.forEach { (_, v) -> v.close() }
    }

    override suspend fun send(message: T) {
        inputChannel.send(message)
    }

    override fun destroy(): Boolean = inputChannel.close()

    override fun getOutputChannel(): ReceiveChannel<W> = outputChannel as Channel<W>
}

open class OneWayRouter<T : Any>(
    processors: Collection<Processor<T, Unit>>,
    groupBy: ((T) -> Any),
    inputChannel: Channel<T> = Channel(Channel.BUFFERED)
) : TwoWayRouter<T, Unit>(processors, groupBy, inputChannel, outputChannel = null)

fun <T : Any, W : Any> CoroutineScope.twoWayRouter(
    applicationContext: ApplicationContext,
    groupBy: (T) -> Any = { Unit },
    start: Boolean = false
): TwoWayRouter<T, W> {
    val processors: Collection<Processor<T, W>> = getProcessorsFromContext(applicationContext)
    return TwoWayRouter(processors, groupBy).apply {
        if (start) {
            launch { start() }
        }
    }
}

fun <T : Any> CoroutineScope.oneWayRouter(
    applicationContext: ApplicationContext,
    groupBy: (T) -> Any = { Unit },
    start: Boolean = false
): OneWayRouter<T> {
    val processors: Collection<Processor<T, Unit>> = getProcessorsFromContext(applicationContext)
    return OneWayRouter(processors, groupBy).apply {
        if (start) {
            launch { start() }
        }
    }
}

internal fun <T : Any, W : Any> getProcessorsFromContext(applicationContext: ApplicationContext): Collection<Processor<T, W>> {
    val processors = mutableListOf<Processor<T, W>>()
    applicationContext.getBeansWithAnnotation(BotCommand::class.java).forEach {
        processors += it.value as Processor<T, W>
    }
    processors.sortBy { it.priority() }
    return processors
}