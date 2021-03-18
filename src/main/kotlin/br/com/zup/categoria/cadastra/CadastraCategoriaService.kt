package br.com.zup.categoria.cadastra

import br.com.zup.categoria.Categoria
import br.com.zup.categoria.CategoriaRepository
import br.com.zup.shared.validacoes.ValidaUnico
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Validated
@Singleton
class CadastraCategoriaService(val repository: CategoriaRepository) {

    @Transactional
    fun cadastrar(@NotBlank @ValidaUnico(campo = "nome", tabela = "Categoria") nome: String) : Categoria {
        val categoria = Categoria(nome)
        repository.save(categoria)
        return categoria
    }
}