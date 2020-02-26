package com.bot.telegram.gamemaster.utils

enum class MessageType {
    SEND_MESSAGES {
        fun apply(): String = "/sendMessages"
    },
    RESTRICT_CHAT_MEMBER {
        fun apply(): String = "/restrictChatMember"
    };
}