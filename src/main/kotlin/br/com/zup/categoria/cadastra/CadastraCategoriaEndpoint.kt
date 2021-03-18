package br.com.zup.categoria.cadastra

import br.com.zup.CadastraNovaCategoriaServiceGrpc
import br.com.zup.NovaCategoriaRequest
import br.com.zup.NovaCategoriaResponse
import br.com.zup.shared.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@ErrorHandler
class CadastraCategoriaEndpoint(val service: CadastraCategoriaService) : CadastraNovaCategoriaServiceGrpc.CadastraNovaCategoriaServiceImplBase() {

    override fun cadastrar(request: NovaCategoriaRequest, responseObserver: StreamObserver<NovaCategoriaResponse>) {

        val categoriaSalva = service.cadastrar(request.nome)

        responseObserver.onNext(NovaCategoriaResponse.newBuilder()
            .setCategoriaId(categoriaSalva.id!!)
            .build())

        responseObserver.onCompleted()
    }
}