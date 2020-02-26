package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

class ChatPermissions(
        @JsonProperty("can_send_messages")
        var canSendMessages: Boolean? = true,
        @JsonProperty("can_send_media_messages")
        var canSendMediaMessages: Boolean? = true,
        @JsonProperty("can_send_polls")
        var canSendPolls: Boolean? = true,
        @JsonProperty("can_send_other_messages")
        var canSendOtherMessages: Boolean? = true,
        @JsonProperty("can_add_web_page_previews")
        var canAddWebPagePreviews: Boolean? = true,
        @JsonProperty("can_change_info")
        var canChangeInfo: Boolean? = true,
        @JsonProperty("can_invite_users")
        var canInviteUsers: Boolean? = true,
        @JsonProperty("can_pin_messages")
        var canPinMessages: Boolean? = true
)

