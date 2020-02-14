package com.bot.telegram.gamemaster.messages

class User(
        val id: Int,
        val is_bot: Boolean?,
        val first_name: String?,
        val last_name: String?,
        val username: String?,
        val language_code: String?,
        val can_join_groups: Boolean?,
        val can_read_all_group_messages: Boolean?,
        val supports_inline_queries: Boolean?
)