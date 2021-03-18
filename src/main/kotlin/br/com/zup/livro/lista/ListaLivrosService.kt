package br.com.zup.livro.lista

import br.com.zup.ListaLivrosResponse
import br.com.zup.livro.LivroRepository
import io.micronaut.aop.Around
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Around
class ListaLivrosService (val repository: LivroRepository) {

    @Transactional
    fun listar(): List<ListaLivrosResponse.InfoLivros> {
        val listaDeLivros = repository.findAll()

        return listaDeLivros.map {
            ListaLivrosResponse.InfoLivros.newBuilder()
                .setLivroId(it.id!!)
                .setNomeLivro(it.titulo)
                .build()
        }
    }
}