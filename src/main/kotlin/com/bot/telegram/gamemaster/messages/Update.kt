package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty


data class Update(
        val updateId: Int,
        val message: Message?,
        @JsonProperty("edited_message")
        val editedMessage: Message?,
        @JsonProperty("channel_post")
        val channelPost: Message?,
        @JsonProperty("edited_channel_post")
        val editedChannelPost: Message?,
        @JsonProperty("inline_query")
        val inlineQuery: InlineQuery?,
        @JsonProperty("chosen_inline_result")
        val chosenInlineResult: ChosenInlineResult,
        @JsonProperty("callback_query")
        val callbackQuery: CallbackQuery?,
        @JsonProperty("shipping_query")
        val shippingQuery: ShippingQuery?,
        @JsonProperty("pre_checkout_query")
        val preCheckoutQuery: PreCheckoutQuery?,
        val poll: Poll?,
        @JsonProperty("poll_answer")
        val pollAnswer: PollAnswer?
)
