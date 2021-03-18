package br.com.zup.autor.cadastra

import br.com.zup.autor.Autor
import br.com.zup.shared.validacoes.ValidaUnico
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutor(

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:Email
    @field:ValidaUnico(campo = "email", tabela = "Autor")
    val email: String,

    @field:NotBlank
    @field:Size(max = 400)
    val descricao: String
) {
    fun toModel(): Autor {
        return Autor(nome, email, descricao)
    }
}