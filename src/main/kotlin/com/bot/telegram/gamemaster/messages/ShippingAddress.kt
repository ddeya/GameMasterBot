package com.bot.telegram.gamemaster.messages

class ShippingAddress(
        val country_code: String?,
        val state: String?,
        val city: String?,
        val street_line1: String?,
        val street_line2: String?,
        val post_code: String?
)