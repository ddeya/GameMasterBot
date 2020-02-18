package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class PreCheckoutQuery(
        val id: String,
        val from: User,
        val currency: String,
        @JsonProperty("total_amount")
        val totalAmount: Int,
        @JsonProperty("invoice_payload")
        val invoicePayload: String,
        @JsonProperty("shipping_option_id")
        val shippingOptionId: String?,
        @JsonProperty("order_info")
        val orderInfo: OrderInfo?
)
