package br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade

import org.springframework.data.repository.CrudRepository

interface MedicaoUmidadeRepository : CrudRepository<MedicaoUmidade, String> {

    fun findTopByOrderByDataCriacaoDesc(): MedicaoUmidade
}