package br.com.zup.venda.cadastra

import br.com.zup.shared.validacoes.CPFouCNPJ
import br.com.zup.shared.validacoes.ExisteObjeto
import br.com.zup.shared.validacoes.ValidaPaisEstado
import io.micronaut.core.annotation.Introspected
import io.micronaut.validation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
@Validated
@ValidaPaisEstado
class NovaVenda(

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    val sobrenome: String,

    @field:CPFouCNPJ
    val documento: String,

    @field:NotBlank
    val endereco: String,

    @field:NotBlank
    val complemento: String,

    @field:NotBlank
    val cidade: String,

    @field:NotNull
    @field:ExisteObjeto(campo = "id", tabela = "Pais")
    val paisId: Long,

    val estadoId: Long,

    @field:NotBlank
    val telefone: String,

    @field:NotBlank
    val cep: String
)