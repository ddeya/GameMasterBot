package com.bot.telegram.gamemaster.messages


class Update {
    var update_id = 0
    var message: Message? = null
    private val edited_message: Message? = null
    private val channel_post: Message? = null
    private val edited_channel_post: Message? = null

    override fun toString(): String {
        return "Update{" +
                "update_id=" + update_id +
                ", message=" + message +
                '}'
    }
}
