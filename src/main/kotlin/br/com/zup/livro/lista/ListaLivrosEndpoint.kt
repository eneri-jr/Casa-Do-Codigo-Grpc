package br.com.zup.livro.lista

import br.com.zup.ListaLivrosRequest
import br.com.zup.ListaLivrosResponse
import br.com.zup.ListaLivrosServiceGrpc
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class ListaLivrosEndpoint (val service: ListaLivrosService) : ListaLivrosServiceGrpc.ListaLivrosServiceImplBase() {

    override fun listar(request: ListaLivrosRequest, responseObserver: StreamObserver<ListaLivrosResponse>) {

        val listaDeLivros = service.listar()

        responseObserver.onNext(ListaLivrosResponse.newBuilder()
            .addAllLivros(listaDeLivros)
            .build()
        )
        responseObserver.onCompleted()
    }
}