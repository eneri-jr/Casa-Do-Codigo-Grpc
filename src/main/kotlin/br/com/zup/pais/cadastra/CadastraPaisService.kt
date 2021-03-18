package br.com.zup.pais.cadastra

import br.com.zup.pais.Pais
import br.com.zup.pais.PaisRepository
import br.com.zup.shared.validacoes.ValidaUnico
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Singleton
@Validated
class CadastraPaisService(val repository: PaisRepository) {

    @Transactional
    fun cadastrar(@NotBlank @ValidaUnico(campo = "nome", tabela = "Pais") nomePais: String) : Pais {
        val pais = Pais(nomePais)
        repository.save(pais)
        return pais
    }
}