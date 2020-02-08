package com.bot.telegram.gamemaster.messages


class Chat {
    var id = 0
    var isIs_bot = false
        private set
    var first_name: String? = null
    var last_name: String? = null
    var type: String? = null
    var username: String? = null

    constructor() {}
    constructor(id: Int, is_bot: Boolean, first_name: String?, last_name: String?, type: String?) {
        this.id = id
        isIs_bot = is_bot
        this.first_name = first_name
        this.last_name = last_name
        this.type = type
    }

    fun setIs_bot(is_bot: Boolean) {
        isIs_bot = is_bot
    }

    override fun toString(): String {
        return "Chat{" +
                "id=" + id +
                ", is_bot=" + isIs_bot +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                '}'
    }
}
