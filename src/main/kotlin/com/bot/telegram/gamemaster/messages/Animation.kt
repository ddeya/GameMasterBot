package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Animation(
        @JsonProperty("file_id")
        val fileId: String,
        @JsonProperty("file_unique_id")
        val fileUniqueId: String,
        val width: Int,
        val height: Int,
        val duration: Int,
        val thumb: PhotoSize?,
        @JsonProperty("file_name")
        val fileName: String?,
        @JsonProperty("mime_type")
        val mimeType: String?,
        @JsonProperty("file_size")
        val fileSize: Int?
)