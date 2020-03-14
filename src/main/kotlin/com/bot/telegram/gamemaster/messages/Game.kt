package com.bot.telegram.gamemaster.messages

data class Game(
        val title: String,
        val description: String,
        val photo: Array<PhotoSize>,
        val text: String,
        val text_entities: Array<MessageEntity>,
        val animation: Animation
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (title != other.title) return false
        if (description != other.description) return false
        if (!photo.contentEquals(other.photo)) return false
        if (text != other.text) return false
        if (!text_entities.contentEquals(other.text_entities)) return false
        if (animation != other.animation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + photo.contentHashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + text_entities.contentHashCode()
        result = 31 * result + animation.hashCode()
        return result
    }
}