package br.com.onurbasfar.monitorAmbiente.config

import com.pengrad.telegrambot.TelegramBot
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableAutoConfiguration
@Configuration
class AppConfig {

    @Bean
    fun telegramBot(@Value("\${telegram-bot.token}") token: String?): TelegramBot {
        return TelegramBot(token)
    }

}