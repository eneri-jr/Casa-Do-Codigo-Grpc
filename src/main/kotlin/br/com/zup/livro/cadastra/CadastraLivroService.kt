package br.com.zup.livro.cadastra

import br.com.zup.autor.AutorRepository
import br.com.zup.categoria.CategoriaRepository
import br.com.zup.livro.Livro
import br.com.zup.livro.LivroRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class CadastraLivroService(
    val livroRepository: LivroRepository,
    val autorRepository: AutorRepository,
    val categoriaRepository: CategoriaRepository
) {

    @Transactional
    fun cadastrar(@Valid novoLivro: NovoLivro): Livro {
        val livro = novoLivro.toModel(autorRepository, categoriaRepository)
        livroRepository.save(livro)
        return livro
    }
}