package br.com.zup.pais

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class Pais(
    @field:NotBlank
    val nome: String
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null
}