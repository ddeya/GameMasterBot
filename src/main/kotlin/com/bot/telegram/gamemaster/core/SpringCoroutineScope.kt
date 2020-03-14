package com.bot.telegram.gamemaster.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import javax.annotation.PreDestroy
import kotlin.coroutines.CoroutineContext

open class SpringCoroutineScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job()

    @PreDestroy
    fun cancelScope() {
        coroutineContext.cancel()
    }
}