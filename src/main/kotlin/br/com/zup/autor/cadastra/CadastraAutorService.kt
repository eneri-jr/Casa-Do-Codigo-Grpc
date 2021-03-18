package br.com.zup.autor.cadastra

import br.com.zup.autor.Autor
import br.com.zup.autor.AutorRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class CadastraAutorService (val repository: AutorRepository) {

    @Transactional
    fun cadastrar(@Valid novoAutor: NovoAutor) : Autor {
        val autor = novoAutor.toModel()
        repository.save(autor)
        return autor
    }
}