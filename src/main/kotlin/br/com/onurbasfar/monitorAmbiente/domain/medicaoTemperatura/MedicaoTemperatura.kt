package br.com.onurbasfar.monitorAmbiente.domain.medicaoTemperatura

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 *  Representa a medição de temperatura em um determinado instante.
 */
@Document
class MedicaoTemperatura(
        var valor: Double
) {
    @Id
    var id: String? = null

    var dataCriacao: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "Temperatura de $valor Celsius"
    }
}