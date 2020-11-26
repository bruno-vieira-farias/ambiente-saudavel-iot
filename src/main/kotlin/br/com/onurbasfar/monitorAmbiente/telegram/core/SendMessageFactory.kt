package br.com.onurbasfar.monitorAmbiente.telegram.core

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup
import com.pengrad.telegrambot.request.SendMessage
import org.springframework.stereotype.Component

@Component
class SendMessageFactory {

    fun cria(chatId: Long, mensagem: String, keyboardLayout: List<Array<String>>): SendMessage {
        val sendMessage = SendMessage(chatId, mensagem)

        if (keyboardLayout.isNotEmpty()) {
            sendMessage.replyMarkup( ReplyKeyboardMarkup(*keyboardLayout.toTypedArray()));
        }

        return sendMessage
    }

}