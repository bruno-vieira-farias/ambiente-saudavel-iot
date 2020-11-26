package br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura

import org.springframework.context.ApplicationEvent

class MedicaoTemperaturaRecebidaEvent(source: Any, val valorMedicao: Double) : ApplicationEvent(source)