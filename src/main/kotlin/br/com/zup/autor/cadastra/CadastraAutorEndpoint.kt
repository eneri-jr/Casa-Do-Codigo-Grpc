package br.com.zup.autor.cadastra

import br.com.zup.CadastraNovoAutorServiceGrpc
import br.com.zup.NovoAutorRequest
import br.com.zup.NovoAutorResponse
import br.com.zup.shared.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@ErrorHandler
class CadastraAutorEndpoint (val service:CadastraAutorService) : CadastraNovoAutorServiceGrpc.CadastraNovoAutorServiceImplBase(){

    override fun cadastrar(request: NovoAutorRequest, responseObserver: StreamObserver<NovoAutorResponse>) {
        val novoAutor = request.toModel()

        val autorSalvo = service.cadastrar(novoAutor)

        responseObserver.onNext(NovoAutorResponse.newBuilder()
            .setAutorId(autorSalvo.id!!)
            .build())

        responseObserver.onCompleted()

    }
}