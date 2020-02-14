package com.bot.telegram.gamemaster.messages

class ShippingQuery(
        val id: String?,
        val from: User?,
        val invoice_payload: String?,
        val shipping_address: ShippingAddress?
)
