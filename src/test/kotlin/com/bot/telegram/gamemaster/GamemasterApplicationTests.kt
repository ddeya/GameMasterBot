package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.messages.Chat
import com.bot.telegram.gamemaster.messages.Message
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.messages.User
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class GamemasterApplicationTests {

    @Autowired
    lateinit var router: Router<Update, Any>

    @Test
    fun testKickCommand() = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = User(id = 2, username = "B")
        val msj = Update(
            updateId = 0,
            message = Message(
                messageId = 0,
                from = userA,
                replyToMessage = Message(
                    messageId = 1,
                    from = userB,
                    chat = Chat(
                        id = 0,
                        bot = false,
                        type = "group"
                    )
                ),
                chat = Chat(
                    id = 0,
                    bot = false,
                    type = "group"
                ),
                text = "/kick"
            )
        )
        router.send(msj)
        Assertions.assertEquals("User ${userB.firstName} kicked", router.getOutputChannel()?.receive())
    }
}
