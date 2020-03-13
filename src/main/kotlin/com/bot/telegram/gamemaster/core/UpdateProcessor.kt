package com.bot.telegram.gamemaster.core

import com.bot.telegram.gamemaster.messages.Update

interface UpdateProcessor<W> : Processor<Update, W>