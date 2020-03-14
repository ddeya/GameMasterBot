package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class PhotoSize(
        @JsonProperty("file_id")
        val fileId: String,
        @JsonProperty("file_unique_id")
        val fileUniqueId: String,
        val width: Int,
        val height: Int,
        @JsonProperty("file_size")
        val fileSize: Int?
)