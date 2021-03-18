package br.com.zup.livro.detalha

import br.com.zup.livro.Livro
import br.com.zup.livro.LivroRepository
import br.com.zup.shared.exceptions.LivroInexistenteException
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.constraints.NotNull

@Validated
@Singleton
class DetalhaLivroService (val repository: LivroRepository){

    @Transactional
    fun procurarLivro(@NotNull livroId: Long) : Livro {

        val possivelLivro = repository.findById(livroId)

        if(!possivelLivro.isPresent)
            throw LivroInexistenteException("Livro não cadastrado em nosso banco de dados")

        return possivelLivro.get()
    }
}