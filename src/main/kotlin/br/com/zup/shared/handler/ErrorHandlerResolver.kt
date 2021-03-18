package br.com.zup.shared.handler

import javax.inject.Singleton

@Singleton
class ErrorHandlerResolver(val handlers: List<ExceptionHandler<Exception>>) {

    fun resolve(e: Exception): ExceptionHandler<Exception> {
        val filteredHandlers = handlers.filter { it.supports(e) }
        return filteredHandlers.first()
    }
}