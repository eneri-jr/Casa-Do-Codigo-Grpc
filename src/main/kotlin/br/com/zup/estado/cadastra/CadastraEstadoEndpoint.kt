package br.com.zup.estado.cadastra

import br.com.zup.CadastraNovoEstadoServiceGrpc
import br.com.zup.NovoEstadoRequest
import br.com.zup.NovoEstadoResponse
import br.com.zup.shared.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@ErrorHandler
class CadastraEstadoEndpoint (val service: CadastraEstadoService) : CadastraNovoEstadoServiceGrpc.CadastraNovoEstadoServiceImplBase() {

    override fun cadastrar(request: NovoEstadoRequest, responseObserver: StreamObserver<NovoEstadoResponse>) {
        val novoEstado = request.toModel()

        val estadoSalvo = service.cadastrar(novoEstado)

        responseObserver.onNext(NovoEstadoResponse.newBuilder()
            .setEstadoId(estadoSalvo.id!!)
            .build())

        responseObserver.onCompleted()
    }
}