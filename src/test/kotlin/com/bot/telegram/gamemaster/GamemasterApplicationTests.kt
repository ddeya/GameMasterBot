package com.bot.telegram.gamemaster

import com.bot.telegram.gamemaster.core.Bot
import com.bot.telegram.gamemaster.messages.Chat
import com.bot.telegram.gamemaster.messages.Message
import com.bot.telegram.gamemaster.messages.Update
import com.bot.telegram.gamemaster.messages.User
import com.bot.telegram.gamemaster.services.CHATTYPE
import com.bot.telegram.gamemaster.services.TelegramAPI
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class GamemasterApplicationTests {

    @Autowired
    lateinit var bot: Bot<Update, Any>

    @Autowired
    lateinit var telegramAPI: TelegramAPI

    //############ Starts Kick Test ######################

    @ParameterizedTest
    @EnumSource(CHATTYPE::class)
    fun `Kick On Different Chat Types`(chatType: CHATTYPE): Unit = runBlocking {
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
                                        type = chatType.toString()
                                )
                        ),
                        chat = Chat(
                                id = 0,
                                bot = false,
                                type = chatType.toString()
                        ),
                        text = "/kick"
                )
        )
        bot.send(msg)
        Assertions.assertEquals("User ${userB.firstName} kicked", bot.getOutputChannel()?.receive())
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
                                type = CHATTYPE.GROUP.toString()
                        ),
                        text = "/kick"
                )
        )
        bot.send(msg)
        Assertions.assertTrue((bot.getOutputChannel()?.receive() as String).isEmpty())
    }

    @Test
    fun `Kick Own Bot`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = telegramAPI.botUser
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
                                        type = CHATTYPE.GROUP.toString()
                                )
                        ),
                        chat = Chat(
                                id = 0,
                                bot = false,
                                type = CHATTYPE.GROUP.toString()
                        ),
                        text = "/kick"
                )
        )
        bot.send(msg)
        Assertions.assertEquals("User @${userA.username}, nice try", bot.getOutputChannel()?.receive())
    }

    //############ starts Mute Test ######################

    @Test
    fun `Mute On SUPERGROUP Chat Types`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = User(id = 2, username = "B")
        val msg = Update(
                updateId = 0,
                message = Message(
                        messageId = 0,
                        from = userA,
                        replyToMessage = Message(messageId = 1, from = userB, chat = Chat(id = 0, bot = false, type = CHATTYPE.SUPERGROUP.toString())),
                        chat = Chat(id = 0, bot = false, type = CHATTYPE.SUPERGROUP.toString()),
                        text = "/mute"
                )
        )
        bot.send(msg)
        Assertions.assertEquals("User ${userB.firstName} silence 🤫 ", bot.getOutputChannel()?.receive())
    }

    @Test
    fun `Mute Without Reply`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val msg = Update(
                updateId = 0,
                message = Message(
                        messageId = 0,
                        from = userA,
                        chat = Chat(
                                id = 0,
                                bot = false,
                                type = CHATTYPE.GROUP.toString()
                        ),
                        text = "/mute"
                )
        )
        bot.send(msg)
        Assertions.assertTrue((bot.getOutputChannel()?.receive() as String).isEmpty())
    }

    @Test
    fun `Mute Own Bot`(): Unit = runBlocking {
        val userA = User(id = 1, username = "A")
        val userB = telegramAPI.botUser
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
                                        type = CHATTYPE.GROUP.toString()
                                )
                        ),
                        chat = Chat(
                                id = 0,
                                bot = false,
                                type = CHATTYPE.GROUP.toString()
                        ),
                        text = "/mute"
                )
        )
        bot.send(msg)
        Assertions.assertEquals("User @${userA.username},really? You can do that", bot.getOutputChannel()?.receive())
    }

}
