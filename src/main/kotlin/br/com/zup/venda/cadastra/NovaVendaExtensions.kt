package br.com.zup.venda.cadastra

import br.com.zup.NovaVendaRequest
import io.micronaut.validation.validator.Validator
import javax.validation.ConstraintViolationException

fun NovaVendaRequest.toModel(validator: Validator): NovaVenda {
    val novaVenda = NovaVenda(email, nome, sobrenome, documento, endereco,
    complemento, cidade,  paisId, estadoId, telefone, cep)

    val validation = validator.validate(novaVenda)
    if(!validation.isEmpty())
        throw ConstraintViolationException(validation)

    return novaVenda
}