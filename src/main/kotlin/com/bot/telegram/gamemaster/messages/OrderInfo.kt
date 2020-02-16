package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class OrderInfo(
        val name: String?,
        @JsonProperty("phone_number")
        val phoneNumber: String?,
        val email: String?,
        @JsonProperty("shipping_address")
        val shippingAddress: ShippingAddress?
)
