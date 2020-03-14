package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Sticker(
        @JsonProperty("file_id")
        val fileId: String,
        @JsonProperty("file_unique_id")
        val fileUniqueId: String,
        val width: Int,
        val height: Int,
        @JsonProperty("is_animated")
        val isAnimated: Boolean?,
        val emoji: String?,
        @JsonProperty("set_name")
        val setName: String?,
        @JsonProperty("mask_position")
        // val maskPosition: MaskPosition?,
        val file_size: Int?
)