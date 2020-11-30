package br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura

import org.springframework.data.repository.CrudRepository

interface MedicaoTemperaturaRepository : CrudRepository<MedicaoTemperatura, String> {
    fun findTopByOrderByDataCriacaoDesc(): MedicaoTemperatura
}