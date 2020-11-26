package br.com.onurbasfar.monitorAmbiente.domain

import br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade.MedicaoUmidade
import br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade.MedicaoUmidadeRecebidaEvent
import br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade.MedicaoUmidadeRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class MedicaoUmidadeService(
        val umidadeRepository: MedicaoUmidadeRepository
) {

    @EventListener
    fun salvaMedicaoTemperatura(event: MedicaoUmidadeRecebidaEvent) {
        val medicaoUmidade = MedicaoUmidade(event.valorMedicao)
        umidadeRepository.save(medicaoUmidade)
    }

}