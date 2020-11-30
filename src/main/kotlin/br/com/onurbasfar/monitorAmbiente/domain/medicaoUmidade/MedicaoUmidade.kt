package br.com.onurbasfar.monitorAmbiente.domain.medicaoUmidade

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 *  Representa a medição de umidade em um determinado instante.
 */
@Document
class MedicaoUmidade(
        val valor:Double
){
    @Id
    var id: String? = null

    var dataCriacao: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "Umidade de $valor%"
    }
}