package br.com.zup.shared.handler.exceptions

import br.com.zup.shared.exceptions.LivroInexistenteException
import br.com.zup.shared.handler.ExceptionHandler
import io.grpc.Status
import javax.inject.Singleton

@Singleton
class LivroInexistenteExceptionHandler : ExceptionHandler<LivroInexistenteException> {
    override fun handle(e: LivroInexistenteException): ExceptionHandler.StatusWrapper {
        return ExceptionHandler.StatusWrapper (
            Status.NOT_FOUND
                .withDescription(e.message)
                .withCause(e)
                )
    }

    override fun supports(e: Exception): Boolean {
        return e is LivroInexistenteException
    }


}