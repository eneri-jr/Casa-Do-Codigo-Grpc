package br.com.zup.estado.cadastra

import br.com.zup.estado.Estado
import br.com.zup.estado.EstadoRepository
import br.com.zup.pais.PaisRepository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class CadastraEstadoService(
    val estadoRepository: EstadoRepository,
    val paisRepository: PaisRepository
) {

    @Transactional
    fun cadastrar(@Valid novoEstado: NovoEstado): Estado {
        val estado = novoEstado.toModel(paisRepository)
        estadoRepository.save(estado)
        return estado
    }
}