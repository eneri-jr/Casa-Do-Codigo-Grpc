package br.com.zup.estado.cadastra

import br.com.zup.NovoEstadoRequest

fun NovoEstadoRequest.toModel(): NovoEstado {
    return NovoEstado(
        nome, paisId
    )
}