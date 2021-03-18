package br.com.zup.estado

import br.com.zup.pais.Pais
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Estado(

    @field:NotBlank
    val nome: String,

    @field:ManyToOne
    @field:NotNull
    val pais: Pais
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}