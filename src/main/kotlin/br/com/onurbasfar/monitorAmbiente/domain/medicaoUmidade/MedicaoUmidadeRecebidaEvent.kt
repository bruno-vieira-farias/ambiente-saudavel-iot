package br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade

import org.springframework.context.ApplicationEvent

class MedicaoUmidadeRecebidaEvent(source: Any, val valorMedicao: Double) : ApplicationEvent(source)