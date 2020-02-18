package com.bot.telegram.gamemaster.messages

import java.util.*


data class Message(val messageId: Int, val from: User?, val chat: Chat, val date: Date?, val text: String?)