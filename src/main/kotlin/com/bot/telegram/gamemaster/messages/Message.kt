package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Message(
    @JsonProperty("message_id")
    val messageId: Int,
    val from: User? = null,
    val date: Date? = null,
    val chat: Chat,
    @JsonProperty("forward_from")
    val forwardFrom: User? = null,
    @JsonProperty("forward_from_chat")
    val forwardFromChat: Chat? = null,
    @JsonProperty("forward_from_message_id")
    val forwardfromMessageId: Int? = null,
    @JsonProperty("forward_signature")
    val forwardSignature: String? = null,
    @JsonProperty("forward_sender_name")
    val forwardSenderName: String? = null,
    @JsonProperty("forward_date")
    val forwardDate: Date? = null,
    @JsonProperty("reply_to_message")
    val replyToMessage: Message? = null,
    @JsonProperty("edit_date")
    val editDate: Date? = null,
    @JsonProperty("media_group_id")
    val mediaGroupId: String? = null,
    @JsonProperty("author_signature")
    val authorSignature: String? = null,
    val text: String? = null,
    val entities: Array<MessageEntity>? = null,
    @JsonProperty("caption_entities")
    val captionEntities: Array<MessageEntity>? = null,
    val audio: Audio? = null,
    val document: Document? = null,
    val animation: Animation? = null,
    val game: Game? = null,
    val photo: Array<PhotoSize>? = null,
    val sticker: Sticker? = null,
    val video: Video? = null,
    val voice: Voice? = null,
    @JsonProperty("video_note")
    val videoNote: VideoNote? = null,
    val caption: String? = null,
    val contact: Contact? = null,
    val location: Location? = null,
    val venue: Venue? = null,
    //poll:	Poll?,
    @JsonProperty("new_chat_members")
    val new_chat_members: Array<User>? = null,
    @JsonProperty("left_chat_member")
    val leftChatMember: User? = null,
    @JsonProperty("new_chat_title")
    val newChatTitle: String? = null,
    @JsonProperty("new_chat_photo")
    val newChatPhoto: Array<PhotoSize>? = null,
    @JsonProperty("delete_chat_photo")
    val deleteChatPhoto: Boolean? = null,
    @JsonProperty("group_chat_created")
    val groupChatCreated: Boolean? = null,
    @JsonProperty("supergroup_chat_created")
    val supergroupChatCreated: Boolean? = null,
    @JsonProperty("channel_chat_created")
    val channelChatCreated: Boolean? = null,
    @JsonProperty("migrate_to_chat_id")
    val migrateToChatId: Int? = null,
    @JsonProperty("migrate_from_chat_id")
    val migrateFromChatId: Int? = null,
    @JsonProperty("pinned_message")
    val pinnedMessage: Message? = null,
//        val invoice	:Invoice?,
//        @JsonProperty("successful_payment")
//        val successful_payment: SuccessfulPayment?,
    @JsonProperty("connected_website")
    val connectedWebsite: String? = null
//        @JsonProperty("passport_data")
//        val passport_data: PassportData?,
//        @JsonProperty("reply_markup")
//        val replyMarkup: InlineKeyboardMarkup?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (messageId != other.messageId) return false
        if (from != other.from) return false
        if (date != other.date) return false
        if (chat != other.chat) return false
        if (forwardFrom != other.forwardFrom) return false
        if (forwardFromChat != other.forwardFromChat) return false
        if (forwardfromMessageId != other.forwardfromMessageId) return false
        if (forwardSignature != other.forwardSignature) return false
        if (forwardSenderName != other.forwardSenderName) return false
        if (forwardDate != other.forwardDate) return false
        if (replyToMessage != other.replyToMessage) return false
        if (editDate != other.editDate) return false
        if (mediaGroupId != other.mediaGroupId) return false
        if (authorSignature != other.authorSignature) return false
        if (text != other.text) return false
        if (entities != null) {
            if (other.entities == null) return false
            if (!entities.contentEquals(other.entities)) return false
        } else if (other.entities != null) return false
        if (captionEntities != null) {
            if (other.captionEntities == null) return false
            if (!captionEntities.contentEquals(other.captionEntities)) return false
        } else if (other.captionEntities != null) return false
        if (audio != other.audio) return false
        if (document != other.document) return false
        if (animation != other.animation) return false
        if (game != other.game) return false
        if (photo != null) {
            if (other.photo == null) return false
            if (!photo.contentEquals(other.photo)) return false
        } else if (other.photo != null) return false
        if (sticker != other.sticker) return false
        if (video != other.video) return false
        if (voice != other.voice) return false
        if (videoNote != other.videoNote) return false
        if (caption != other.caption) return false
        if (contact != other.contact) return false
        if (location != other.location) return false
        if (venue != other.venue) return false
        if (new_chat_members != null) {
            if (other.new_chat_members == null) return false
            if (!new_chat_members.contentEquals(other.new_chat_members)) return false
        } else if (other.new_chat_members != null) return false
        if (leftChatMember != other.leftChatMember) return false
        if (newChatTitle != other.newChatTitle) return false
        if (newChatPhoto != null) {
            if (other.newChatPhoto == null) return false
            if (!newChatPhoto.contentEquals(other.newChatPhoto)) return false
        } else if (other.newChatPhoto != null) return false
        if (deleteChatPhoto != other.deleteChatPhoto) return false
        if (groupChatCreated != other.groupChatCreated) return false
        if (supergroupChatCreated != other.supergroupChatCreated) return false
        if (channelChatCreated != other.channelChatCreated) return false
        if (migrateToChatId != other.migrateToChatId) return false
        if (migrateFromChatId != other.migrateFromChatId) return false
        if (pinnedMessage != other.pinnedMessage) return false
        if (connectedWebsite != other.connectedWebsite) return false

        return true
    }

    override fun hashCode(): Int {
        var result = messageId
        result = 31 * result + (from?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + chat.hashCode()
        result = 31 * result + (forwardFrom?.hashCode() ?: 0)
        result = 31 * result + forwardFromChat.hashCode()
        result = 31 * result + (forwardfromMessageId?.hashCode() ?: 0)
        result = 31 * result + forwardSignature.hashCode()
        result = 31 * result + forwardSenderName.hashCode()
        result = 31 * result + (forwardDate?.hashCode() ?: 0)
        result = 31 * result + (replyToMessage?.hashCode() ?: 0)
        result = 31 * result + (editDate?.hashCode() ?: 0)
        result = 31 * result + (mediaGroupId?.hashCode() ?: 0)
        result = 31 * result + (authorSignature?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (entities?.contentHashCode() ?: 0)
        result = 31 * result + (captionEntities?.contentHashCode() ?: 0)
        result = 31 * result + (audio?.hashCode() ?: 0)
        result = 31 * result + (document?.hashCode() ?: 0)
        result = 31 * result + (animation?.hashCode() ?: 0)
        result = 31 * result + (game?.hashCode() ?: 0)
        result = 31 * result + (photo?.contentHashCode() ?: 0)
        result = 31 * result + (sticker?.hashCode() ?: 0)
        result = 31 * result + (video?.hashCode() ?: 0)
        result = 31 * result + (voice?.hashCode() ?: 0)
        result = 31 * result + (videoNote?.hashCode() ?: 0)
        result = 31 * result + (caption?.hashCode() ?: 0)
        result = 31 * result + (contact?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (venue?.hashCode() ?: 0)
        result = 31 * result + (new_chat_members?.contentHashCode() ?: 0)
        result = 31 * result + (leftChatMember?.hashCode() ?: 0)
        result = 31 * result + (newChatTitle?.hashCode() ?: 0)
        result = 31 * result + (newChatPhoto?.contentHashCode() ?: 0)
        result = 31 * result + (deleteChatPhoto.hashCode() ?: 0)
        result = 31 * result + (groupChatCreated.hashCode() ?: 0)
        result = 31 * result + (supergroupChatCreated.hashCode() ?: 0)
        result = 31 * result + (channelChatCreated.hashCode() ?: 0)
        result = 31 * result + (migrateToChatId ?: 0)
        result = 31 * result + (migrateFromChatId ?: 0)
        result = 31 * result + (pinnedMessage?.hashCode() ?: 0)
        result = 31 * result + (connectedWebsite?.hashCode() ?: 0)
        return result
    }
}