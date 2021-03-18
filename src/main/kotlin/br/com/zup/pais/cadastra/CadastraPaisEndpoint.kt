package br.com.zup.pais.cadastra

import br.com.zup.CadastraNovoPaisServiceGrpc
import br.com.zup.NovoAutorResponse
import br.com.zup.NovoPaisRequest
import br.com.zup.NovoPaisResponse
import br.com.zup.shared.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@ErrorHandler
class CadastraPaisEndpoint(val service: CadastraPaisService) : CadastraNovoPaisServiceGrpc.CadastraNovoPaisServiceImplBase(){

    override fun cadastrar(request: NovoPaisRequest, responseObserver: StreamObserver<NovoPaisResponse>) {

        val paisSalvo = service.cadastrar(request.nome)

        responseObserver.onNext(NovoPaisResponse.newBuilder()
            .setPaisId(paisSalvo.id!!)
            .build())

        responseObserver.onCompleted()

    }
}