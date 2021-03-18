package br.com.zup.livro.cadastra

import br.com.zup.CadastraNovoLivroServiceGrpc
import br.com.zup.NovoAutorResponse
import br.com.zup.NovoLivroRequest
import br.com.zup.NovoLivroResponse
import br.com.zup.shared.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@ErrorHandler
class CadastraLivroEndpoint (val service: CadastraLivroService) : CadastraNovoLivroServiceGrpc.CadastraNovoLivroServiceImplBase() {

    override fun cadastrar(request: NovoLivroRequest, responseObserver: StreamObserver<NovoLivroResponse>) {

        val novoLivro = request.toModel()

        val livroSalvo = service.cadastrar(novoLivro)

        responseObserver.onNext(NovoLivroResponse.newBuilder()
            .setLivroId(livroSalvo.id!!)
            .build())

        responseObserver.onCompleted()
    }
}