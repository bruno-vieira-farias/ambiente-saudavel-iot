package br.com.onurbasfar.monitorAmbiente.telegram.core

import br.com.onurbasfar.monitorAmbiente.arduino.ArduinoService
import br.com.onurbasfar.monitorAmbiente.arduino.ComandoArduino
import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperaturaRepository
import br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade.MedicaoUmidadeRepository
import org.springframework.stereotype.Service

@Service
class TratadorMensagemService(
        private val geradorMensagemInicialService: GeradorMensagemInicialService,
        private val medicaoTemperaturaRepository: MedicaoTemperaturaRepository,
        private val medicaoUmidadeRepository: MedicaoUmidadeRepository,
        private val arduinoService: ArduinoService
) {

    fun trataMensagemRecebida(mensagem: String?): List<Resposta> {
        return when (mensagem) {
            "/start" -> geradorMensagemInicialService.geraMensagemInicial()
            "Obter Temperatura" -> {
                val medicaoTemperatura = medicaoTemperaturaRepository.findTopByOrderByDataCriacaoDesc()
                listOf(Resposta("A temperatura do ambiente é de ${medicaoTemperatura.valor} Celsius."))
            }
            "Obter Umidade" -> {
                val medicaoUmidade = medicaoUmidadeRepository.findTopByOrderByDataCriacaoDesc()
                listOf(Resposta("A umidade do ambiente ẽ de ${medicaoUmidade.valor}%."))
            }
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
            "Ativar modo automatico" -> {
                arduinoService.enviaComando(ComandoArduino.ATIVAR_MODO_AUTOMATICO)
                listOf(Resposta("Desligando..."))
            }
            "Desativar modo automatico" -> {
                arduinoService.enviaComando(ComandoArduino.DESATIVAR_MODO_AUTOMATICO)
                listOf(Resposta("Desligando..."))
            }
            else -> {
                listOf(Resposta("desculpe, não entendi."))
            }
        }
    }
}