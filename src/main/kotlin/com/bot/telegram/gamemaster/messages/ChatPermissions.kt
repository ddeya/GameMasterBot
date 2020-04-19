package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatPermissions(
        @JsonProperty("can_send_messages")
        val canSendMessages: Boolean?= true,
        @JsonProperty("can_send_media_messages")
        val canSendMediaMessages: Boolean?= true,
        @JsonProperty("can_send_polls")
        val canSendPolls: Boolean?= true,
        @JsonProperty("can_send_other_messages")
        val canSendOtherMessages: Boolean?= true,
        @JsonProperty("can_add_web_page_previews")
        val canAddWebPagePreviews: Boolean?= true,
        @JsonProperty("can_change_info")
        val canChangeInfo: Boolean?= true,
        @JsonProperty("can_invite_users")
        val canInviteUsers: Boolean?= true,
        @JsonProperty("can_pin_messages")
        val canPinMessages: Boolean?= true
)