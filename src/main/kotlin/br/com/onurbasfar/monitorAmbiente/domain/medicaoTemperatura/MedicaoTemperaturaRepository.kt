package br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura

import br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura.MedicaoTemperatura
import org.springframework.data.repository.CrudRepository

interface MedicaoTemperaturaRepository : CrudRepository<MedicaoTemperatura, String>
{
    fun findTopByOrderByDataCriacaoDesc(): MedicaoTemperatura

}