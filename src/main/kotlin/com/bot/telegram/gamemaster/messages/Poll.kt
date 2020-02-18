package com.bot.telegram.gamemaster.messages

import com.fasterxml.jackson.annotation.JsonProperty

data class Poll(
        val id: String,
        val question: String,
        val options: Array<PollOption>,
        @JsonProperty("total_voter_count")
        val totalVoterCount: Int,
        @JsonProperty("is_closed")
        val isClosed: Boolean,
        @JsonProperty("is_anonymous")
        val isAnonymous: Boolean,
        val type: String,
        @JsonProperty("allows_multiple_answers")
        val allowsMultipleAnswers: Boolean,
        @JsonProperty("correct_option_id")
        val correctOptionId: Int?
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Poll

                if (id != other.id) return false
                if (question != other.question) return false
                if (!options.contentEquals(other.options)) return false
                if (totalVoterCount != other.totalVoterCount) return false
                if (isClosed != other.isClosed) return false
                if (isAnonymous != other.isAnonymous) return false
                if (type != other.type) return false
                if (allowsMultipleAnswers != other.allowsMultipleAnswers) return false
                if (correctOptionId != other.correctOptionId) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + question.hashCode()
                result = 31 * result + options.contentHashCode()
                result = 31 * result + totalVoterCount
                result = 31 * result + isClosed.hashCode()
                result = 31 * result + isAnonymous.hashCode()
                result = 31 * result + type.hashCode()
                result = 31 * result + allowsMultipleAnswers.hashCode()
                result = 31 * result + (correctOptionId ?: 0)
                return result
        }
}