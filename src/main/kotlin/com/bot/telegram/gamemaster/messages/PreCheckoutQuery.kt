package com.bot.telegram.gamemaster.messages

class PreCheckoutQuery(
        val id: String,
        val from: User,
        val currency: String,
        val total_amount: Int,
        val invoice_payload: String,
        val shipping_option_id: String?,
        val order_info: OrderInfo?
)
