package br.com.zup.livro

import br.com.zup.autor.Autor
import br.com.zup.categoria.Categoria
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Livro (

    @field:NotBlank
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
    val isbn: String,

    @field:NotNull
    @field:Future
    val dataPublicacao: LocalDateTime,

    @field:ManyToOne
    @field:NotNull
    val categoria: Categoria,

    @field:ManyToOne
    @field:NotNull
    val autor: Autor

    ){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}