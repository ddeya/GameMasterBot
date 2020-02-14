package com.bot.telegram.gamemaster.messages

class OrderInfo(
        val name: String?,
        val phone_number: String?,
        val email: String?,
        val shipping_address: ShippingAddress?
)
