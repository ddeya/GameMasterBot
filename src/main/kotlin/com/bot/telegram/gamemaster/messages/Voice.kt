package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Voice(
        @JsonProperty("file_id")
        val fileId: String,
        @JsonProperty("file_unique_id")
        val fileUniqueId: String,
        val duration: Int,
        @JsonProperty("mime_type")
        val mimeType: String?,
        @JsonProperty("file_size")
        val fileSize: Int?
)