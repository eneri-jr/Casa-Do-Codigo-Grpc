package br.com.zup.estado.cadastra

import br.com.zup.estado.Estado
import br.com.zup.pais.PaisRepository
import br.com.zup.shared.validacoes.ExisteObjeto
import br.com.zup.shared.validacoes.ValidaUnico
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class NovoEstado(

    @field:NotBlank
    @field:ValidaUnico(campo = "nome", tabela = "Estado")
    val nome: String,

    @field:NotNull
    @field:ExisteObjeto(campo = "id", tabela = "Pais")
    val paisId: Long
) {
    fun toModel(paisRepository: PaisRepository) : Estado {
        val pais = paisRepository.findById(paisId)
        return Estado(nome, pais.get())
    }
}