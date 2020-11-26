package br.com.onurbasfar.monitorAmbiente.arduino

import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperaturaRecebidaEvent
import br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade.MedicaoUmidadeRecebidaEvent
import com.fazecast.jSerialComm.SerialPort
import com.fazecast.jSerialComm.SerialPortEvent
import com.fazecast.jSerialComm.SerialPortMessageListener
import org.springframework.context.ApplicationEventPublisher

class Listener(
        private val eventPublisher: ApplicationEventPublisher
) : SerialPortMessageListener {

    override fun getMessageDelimiter(): ByteArray {
        return byteArrayOf(';'.toByte())
    }

    override fun delimiterIndicatesEndOfMessage(): Boolean {
        return true
    }

    override fun getListeningEvents(): Int {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED
    }

    override fun serialEvent(event: SerialPortEvent?) {
        val dadosRecebidos = event!!.receivedData
                .map { it.toChar() }
                .joinToString("")

        eventPublisher.publishEvent(MedicaoTemperaturaRecebidaEvent(this, extraiMedicaoTemperatura(dadosRecebidos)))
        eventPublisher.publishEvent(MedicaoUmidadeRecebidaEvent(this, extraiMedicaoUmidade(dadosRecebidos)))

    }

    private fun extraiMedicaoTemperatura(dadosRecebidos: String): Double {
        return dadosRecebidos.substring(2, 7).toDouble()
    }

    private fun extraiMedicaoUmidade(dadosRecebidos: String): Double {
        return dadosRecebidos.substring(10, 15).toDouble()
    }
}