package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class ShippingAddress(
        @JsonProperty("country_code")
        val countryCode: String?,
        val state: String?,
        val city: String?,
        @JsonProperty("street_line1")
        val streetLine1: String?,
        @JsonProperty("street_line2")
        val streetLine2: String?,
        @JsonProperty("post_code")
        val postCode: String?
)