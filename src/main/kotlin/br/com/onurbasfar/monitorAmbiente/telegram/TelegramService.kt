package br.com.onurbasfar.monitorAmbiente.telegram

import br.com.onurbasfar.monitorAmbiente.telegram.core.Resposta
import br.com.onurbasfar.monitorAmbiente.telegram.core.SendMessageFactory
import br.com.onurbasfar.monitorAmbiente.telegram.core.TratadorMensagemService
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.ChatAction
import com.pengrad.telegrambot.request.SendChatAction
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class TelegramService(
        private val bot: TelegramBot,
        private val tratadorMensagemService: TratadorMensagemService,
        private val sendMessageFactory: SendMessageFactory
){

    /**
     * Inicia o bot para interação com o usuário.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun iniciaBot() {
        bot.setUpdatesListener { updates: List<Update> ->
            updates.forEach(
                    Consumer {  processaMensagemRecebida(it.message().text(), it.message().chat().id()) }
            )
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }

    /**
     * Trata a mensagem recebida de acordo com as interações do usuário.
     *
     * @param mensagem é o input do usuario no bot.
     * @param chatId é um idenficador que representa uma chat ativo.
     */
    private fun processaMensagemRecebida(mensagem: String, chatId: Long) {
        bot.execute(SendChatAction(chatId, ChatAction.typing.name))
        val respostas: List<Resposta> = tratadorMensagemService.trataMensagemRecebida(mensagem)
        responde(chatId, respostas)
    }

    /**
     * Responde a uma interação do usuário.
     *
     * @param chatId é um idenficador que representa uma chat ativo.
     * @param respostas retorno enviado para o bot que será apresentado para o usuário.
     */
    private fun responde(chatId: Long, respostas: List<Resposta>) {
        respostas.forEach {
            val message = sendMessageFactory.cria(chatId, it.mensagem, it.keyboardLayout)
            bot.execute(message)
        }
    }
}