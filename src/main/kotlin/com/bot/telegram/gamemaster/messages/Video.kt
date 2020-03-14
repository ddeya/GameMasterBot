package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Video(
        @JsonProperty("file_id")
        val fileId: String,
        @JsonProperty("file_unique_id")
        val fileUniqueId: String,
        val width: Int,
        val height: Int,
        val duration: Int,
        val thumb: PhotoSize?,
        @JsonProperty("mime_type")
        val mimeType: String?,
        @JsonProperty("file_size")
        val fileSize: Int?
)