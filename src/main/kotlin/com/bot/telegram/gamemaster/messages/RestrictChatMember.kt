package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class RestrictChatMember(
        @JsonProperty("chat_id")
        var chatId: Long,
        @JsonProperty("user_id")
        var userId: Long,
        var permissions: ChatPermissions,
        @JsonProperty("until_date")
        var untilDate: Long?
)