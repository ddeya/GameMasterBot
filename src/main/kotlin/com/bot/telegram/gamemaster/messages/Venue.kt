package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Venue(
        val location: Location,
        val title: String,
        val address: String,
        @JsonProperty("foursquare_id")
        val foursquareId: String?,
        @JsonProperty("foursquare_type")
        val foursquareType: String?
)