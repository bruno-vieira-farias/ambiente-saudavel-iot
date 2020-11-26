package br.com.onurbasfar.monitorAmbiente.telegram.core

import br.com.onurbasfar.monitorAmbiente.arduino.ArduinoService
import br.com.onurbasfar.monitorAmbiente.arduino.ComandoArduino
import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperaturaRepository
import org.springframework.stereotype.Service

@Service
class TratadorMensagemService(
        private val geradorMensagemInicialService: GeradorMensagemInicialService,
        private val medicaoTemperaturaRepository: MedicaoTemperaturaRepository,
        private val arduinoService: ArduinoService
) {

    fun trataMensagemRecebida(mensagem: String?): List<Resposta> {
        return when (mensagem) {
            "/start" -> geradorMensagemInicialService.geraMensagemInicial()
            "Temperatura do ambiente" -> {
                val medicaoTemperatura = medicaoTemperaturaRepository.findTopByOrderByDataCriacaoDesc()
                listOf(Resposta("A temperatura do ambiente é de ${medicaoTemperatura.valor} Celsius."))
            }
//            "Taxa selic acumulada nos últimos 30 dias" -> {
//                textoResposta = "A taxa acumulada nos últimos 30 dias é de \${a}%"
//                listOf<RespostaInvestBot>(criaRespostaInvestBot(textoResposta, taxaSelicService.obtemTaxaSelicUltimosTrintaDias()))
//            }
            "Ligar Ventilador" -> {
                arduinoService.enviaComando(ComandoArduino.LIGAR_VENTILADOR)
                listOf(Resposta("Ligando..."))
            }
            "Desligar Ventilador" -> {
                arduinoService.enviaComando(ComandoArduino.DESLIGAR_VENTILADOR)
                listOf(Resposta("Desligando..."))
            }
            "Ligar Umidificador" -> {
                arduinoService.enviaComando(ComandoArduino.LIGAR_UMIDIFICADOR)
                listOf(Resposta("Ligando..."))
            }
            "Desligar Umidificador" -> {
                arduinoService.enviaComando(ComandoArduino.DESLIGAR_UMIDIFICADOR)
                listOf(Resposta("Desligando..."))
            }
            else -> {
                listOf(Resposta("desculpe, não entendi."))
            }
        }
    }
}