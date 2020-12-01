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
                listOf(Resposta("A umidade do ambiente é de ${medicaoUmidade.valor}%."))
            }
            "Ligar Ventilador" -> {
                arduinoService.enviaComando(ComandoArduino.LIGAR_VENTILADOR)
                listOf(Resposta("Ligando Ventilador..."))
            }
            "Desligar Ventilador" -> {
                arduinoService.enviaComando(ComandoArduino.DESLIGAR_VENTILADOR)
                listOf(Resposta("Desligando Ventilador..."))
            }
            "Ligar Umidificador" -> {
                arduinoService.enviaComando(ComandoArduino.LIGAR_UMIDIFICADOR)
                listOf(Resposta("Ligando Umidificador..."))
            }
            "Desligar Umidificador" -> {
                arduinoService.enviaComando(ComandoArduino.DESLIGAR_UMIDIFICADOR)
                listOf(Resposta("Desligando Umidificador..."))
            }
            "Ativar modo automatico" -> {
                arduinoService.enviaComando(ComandoArduino.ATIVAR_MODO_AUTOMATICO)
                listOf(Resposta("Ativando Modo Automático..."))
            }
            "Desativar modo automatico" -> {
                arduinoService.enviaComando(ComandoArduino.DESATIVAR_MODO_AUTOMATICO)
                listOf(Resposta("Desativando Modo Automático..."))
            }
            else -> {
                listOf(Resposta("desculpe, não entendi."))
            }
        }
    }
}