package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class PollAnswer(
        @JsonProperty("poll_id")
        val pollId: String,
        val user: User,
        @JsonProperty("option_ids")
        val optionIds: Array<Int>
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as PollAnswer

                if (pollId != other.pollId) return false
                if (user != other.user) return false
                if (!optionIds.contentEquals(other.optionIds)) return false

                return true
        }

        override fun hashCode(): Int {
                var result = pollId.hashCode()
                result = 31 * result + user.hashCode()
                result = 31 * result + optionIds.contentHashCode()
                return result
        }
}
