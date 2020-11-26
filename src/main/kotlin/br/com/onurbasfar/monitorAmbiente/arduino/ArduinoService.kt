package br.com.onurbasfar.monitorAmbiente.arduino

import com.fazecast.jSerialComm.SerialPort
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ArduinoService(
        private val eventPublisher: ApplicationEventPublisher
) {
    private val comPort = SerialPort.getCommPorts()[0]

    init {
        setup()
    }

    private fun setup() {
        comPort.openPort()
        comPort.addDataListener(Listener(eventPublisher))
    }

    fun enviaComando(comando: ComandoArduino) {
        comPort.openPort()

        while (!comPort.isOpen) {
            println("Aguardando...")
        }
        val result = comPort.writeBytes("${comando.name};".toByteArray(), comando.name.length.toLong())
        println(result)
    }

}