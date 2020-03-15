package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty


data class Update(
    val updateId: Int,
    val message: Message? = null,
    @JsonProperty("edited_message")
    val editedMessage: Message? = null,
    @JsonProperty("channel_post")
    val channelPost: Message? = null,
    @JsonProperty("edited_channel_post")
    val editedChannelPost: Message? = null,
    @JsonProperty("inline_query")
    val inlineQuery: InlineQuery? = null,
    @JsonProperty("chosen_inline_result")
    val chosenInlineResult: ChosenInlineResult? = null,
    @JsonProperty("callback_query")
    val callbackQuery: CallbackQuery? = null,
    @JsonProperty("shipping_query")
    val shippingQuery: ShippingQuery? = null,
    @JsonProperty("pre_checkout_query")
    val preCheckoutQuery: PreCheckoutQuery? = null,
    val poll: Poll? = null,
    @JsonProperty("poll_answer")
    val pollAnswer: PollAnswer? = null
)
