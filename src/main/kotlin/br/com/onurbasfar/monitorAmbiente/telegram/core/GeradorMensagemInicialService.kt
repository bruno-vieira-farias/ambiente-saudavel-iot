package br.com.onurbasfar.monitorAmbiente.telegram.core

import org.springframework.stereotype.Service

@Service
class GeradorMensagemInicialService {

    /**
     * Gera a mensagem inicial que é o primeiro texto apresentado ao usuario.
     * @return [Resposta]
     */
    fun geraMensagemInicial(): List<Resposta> {
        return listOf(
                Resposta("Parabéns, você esta em contato com a central de monitoramento de temperatura do seu ambiente."),
                Resposta("Selecione uma das opcões do menu para mais informações sobre o ambiente monitorado.", criaLayoutBotoesMenu())
        )
    }

    /**
     * Layout dos botões de iteração que serão apresentados ao usuario.
     *
     * @return retorna o layou no formato de [String[]]
     */
    private fun criaLayoutBotoesMenu(): List<Array<String>> {
        return listOf(
                arrayOf("Temperatura do ambiente", "Umidade do Ambiente"),
                arrayOf("Ligar Ventilador", "Desligar Ventilador"),
                arrayOf("Ligar Umidificador", "Desligar Umidificador")
        )
    }
}