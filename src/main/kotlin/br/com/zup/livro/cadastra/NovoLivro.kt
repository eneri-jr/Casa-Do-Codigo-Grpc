package br.com.zup.livro.cadastra

import br.com.zup.autor.AutorRepository
import br.com.zup.categoria.CategoriaRepository
import br.com.zup.livro.Livro
import br.com.zup.shared.validacoes.ExisteObjeto
import br.com.zup.shared.validacoes.ValidaUnico
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import javax.validation.constraints.*

@Introspected
data class NovoLivro(

    @field:NotBlank
    @field:ValidaUnico(campo = "titulo", tabela = "Livro")
    val titulo: String,

    @field:NotBlank
    @field:Size(max = 500)
    val resumo: String,

    @field:NotBlank
    val sumario: String,

    @field:NotNull
    @field:Min(value = 20)
    val preco: Double,

    @field:NotNull
    @field:Min(value = 100)
    val paginas: Int,

    @field:NotBlank
    @field:ValidaUnico(campo = "isbn", tabela = "Livro")
    val isbn: String,

    @field:NotNull
    @field:Future
    val dataPublicacao: LocalDateTime,

    @field:ExisteObjeto(campo = "id", tabela = "Categoria")
    val categoriaId: Long,

    @field:ExisteObjeto(campo = "id", tabela = "Autor")
    val autorId: Long,

) {
    fun toModel(autorRepository: AutorRepository, categoriaRepository: CategoriaRepository) : Livro {
        val autor = autorRepository.findById(autorId)
        val categoria = categoriaRepository.findById(categoriaId)
        return Livro (titulo, resumo, sumario, preco, paginas, isbn, dataPublicacao, categoria.get(), autor.get())
    }
}