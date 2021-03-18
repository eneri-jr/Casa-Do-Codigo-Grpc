package br.com.zup.autor.cadastra

import br.com.zup.NovoAutorRequest

fun NovoAutorRequest.toModel(): NovoAutor {
    return NovoAutor(
        nome, email, descricao
    )
}