package br.com.onurbasfar.monitorAmbiente.domain

import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperatura
import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperaturaRecebidaEvent
import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperaturaRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class MedicaoTemperaturaService(
        private val medicaoTemperaturaRepository: MedicaoTemperaturaRepository
) {

    @EventListener
    fun salvaMedicaoTemperatura(event: MedicaoTemperaturaRecebidaEvent) {
        val medicaoTemperatura = MedicaoTemperatura(event.valorMedicao)
        medicaoTemperaturaRepository.save(medicaoTemperatura)
    }

}