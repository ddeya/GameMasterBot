package com.bot.telegram.gamemaster.messages

import java.util.*


class Message {
    var message_id = 0
    var from: From? = null
    var chat: Chat? = null
    var date: Date? = null
    var text: String? = null

    constructor() {}
    constructor(message_id: Int, from: From?, chat: Chat?, date: Date?, text: String?) {
        this.message_id = message_id
        this.from = from
        this.chat = chat
        this.date = date
        this.text = text
    }

    override fun toString(): String {
        return "Message{" +
                "message_id=" + message_id +
                ", from=" + from +
                ", chat=" + chat +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}'
    }
}
