package br.com.zup.livro.cadastra

import br.com.zup.NovoLivroRequest
import java.time.LocalDateTime
import java.time.ZoneOffset

fun NovoLivroRequest.toModel(): NovoLivro {
    return NovoLivro(
        titulo, resumo, sumario, preco, paginas, isbn,
        dataPublicacao.let {
            LocalDateTime.ofEpochSecond(
                it.seconds,
                it.nanos,
                ZoneOffset.UTC
            )
        }, categoriaid, autorid
    )
}