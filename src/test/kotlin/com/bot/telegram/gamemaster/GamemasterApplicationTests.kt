package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Router
import com.bot.telegram.gamemaster.messages.Chat
import com.bot.telegram.gamemaster.messages.Message
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.messages.User
import com.bot.telegram.gamemaster.services.CHATTYPE
import com.bot.telegram.gamemaster.services.TelegramAPI
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

    @Autowired
    lateinit var telegramAPI: TelegramAPI

    @Test
    fun `Kick On Group`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = User(id = 2, username = "B")
        val msg = Update(
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
                        type = CHATTYPE.GROUP.value
                    )
                ),
                chat = Chat(
                    id = 0,
                    bot = false,
                    type = CHATTYPE.GROUP.value
                ),
                text = "/kick"
            )
        )
        router.send(msg)
        Assertions.assertEquals("User ${userB.firstName} kicked", router.getOutputChannel()?.receive())
    }

    @Test
    fun `Kick On Supergroup`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = User(id = 2, username = "B")
        val msg = Update(
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
                        type = CHATTYPE.SUPERGROUP.value
                    )
                ),
                chat = Chat(
                    id = 0,
                    bot = false,
                    type = CHATTYPE.SUPERGROUP.value
                ),
                text = "/kick"
            )
        )
        router.send(msg)
        Assertions.assertEquals("User ${userB.firstName} kicked", router.getOutputChannel()?.receive())
    }

    @Test
    fun `Kick Without Reply`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val msg = Update(
            updateId = 0,
            message = Message(
                messageId = 0,
                from = userA,
                chat = Chat(
                    id = 0,
                    bot = false,
                    type = CHATTYPE.GROUP.value
                ),
                text = "/kick"
            )
        )
        router.send(msg)
        Assertions.assertTrue((router.getOutputChannel()?.receive() as String).isEmpty())
    }

    @Test
    fun `Unable to Kick Own Bot`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = telegramAPI.botId
        val msg = Update(
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
                        type = CHATTYPE.GROUP.value
                    )
                ),
                chat = Chat(
                    id = 0,
                    bot = false,
                    type = CHATTYPE.GROUP.value
                ),
                text = "/kick"
            )
        )
        router.send(msg)
        Assertions.assertEquals("User @${userA.username}, nice try", router.getOutputChannel()?.receive())
    }
}
