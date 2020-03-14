package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Contact(
        @JsonProperty("phone_number")
        val phoneNumber: String,
        @JsonProperty("first_name")
        val firstName: String,
        @JsonProperty("last_name")
        val lastName: String?,
        @JsonProperty("user_id")
        val userId: Int?,
        val vcard: String?
)