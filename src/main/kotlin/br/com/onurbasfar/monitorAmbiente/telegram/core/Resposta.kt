package br.com.onurbasfar.monitorAmbiente.telegram.core

class Resposta(
        val mensagem: String,
        val keyboardLayout: List<Array<String>> = emptyList()
)