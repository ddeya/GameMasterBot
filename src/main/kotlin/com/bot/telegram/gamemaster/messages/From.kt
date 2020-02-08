package com.bot.telegram.gamemaster.messages


class From {
    var id = 0
    var isIs_bot = false
        private set
    var first_name: String? = null
    var last_name: String? = null
    var language_code: String? = null
    var type: String? = null
    var username: String? = null

    constructor() {}
    constructor(id: Int, is_bot: Boolean, first_name: String?, last_name: String?, language_code: String?, type: String?) {
        this.id = id
        isIs_bot = is_bot
        this.first_name = first_name
        this.last_name = last_name
        this.language_code = language_code
        this.type = type
    }

    fun setIs_bot(is_bot: Boolean) {
        isIs_bot = is_bot
    }

    override fun toString(): String {
        return "From{" +
                "id=" + id +
                ", is_bot=" + isIs_bot +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", language_code='" + language_code + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                '}'
    }
}
