package com.bot.telegram.gamemaster.messages


data class Update(
        val updateId: Int,
        val message: Message?,
        val edited_message: Message?,
        val channel_post: Message?,
        val edited_channel_post: Message?,
        val editedMessage: Message?,
        val channelPost: Message?,
        val editedChannelPost: Message?,
        val inline_query: InlineQuery?,
        val chosen_inline_result: ChosenInlineResult,
        val callback_query: CallbackQuery?,
        val shipping_query: ShippingQuery?,
        val pre_checkout_query: PreCheckoutQuery?,
        val poll: Poll?,
        val poll_answer: PollAnswer?
)
