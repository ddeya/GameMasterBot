package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class VideoNote(
        @JsonProperty("file_id")
        val fileId: String,
        @JsonProperty("file_unique_id")
        val fileUniqueId: String,
        val length: Int,
        val duration: Int,
        val thumb: PhotoSize?,
        @JsonProperty("file_size")
        val fileSize: Int?
)