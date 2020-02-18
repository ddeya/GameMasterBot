package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class ShippingQuery(
        val id: String?,
        val from: User?,
        @JsonProperty("invoice_payload")
        val invoicePayload: String?,
        @JsonProperty("shipping_address")
        val shippingAddress: ShippingAddress?
)
