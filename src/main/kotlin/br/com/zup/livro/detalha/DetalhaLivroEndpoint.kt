package br.com.zup.livro.detalha

import br.com.zup.*
import br.com.zup.shared.handler.ErrorHandler
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@ErrorHandler
class DetalhaLivroEndpoint(val service: DetalhaLivroService) : DetalhaLivroServiceGrpc.DetalhaLivroServiceImplBase() {

    override fun detalhar(request: DetalhaLivroRequest, responseObserver: StreamObserver<DetalhaLivroResponse>) {

        val livro = service.procurarLivro(request.livroId)

        responseObserver.onNext(
            DetalhaLivroResponse.newBuilder()
                .setTitulo(livro.titulo)
                .setResumo(livro.resumo)
                .setSumario(livro.sumario)
                .setPreco(livro.preco)
                .setPaginas(livro.paginas)
                .setIsbn(livro.isbn)
                .setDataPublicacao(
                    Timestamp.newBuilder()
                        .setNanos(livro.dataPublicacao.nano)
                        .build()
                )
                .setCategoria(livro.categoria.nome)
                .setAutor(
                    Autor.newBuilder()
                        .setNome(livro.autor.nome)
                        .setDescricao(livro.autor.descricao)
                )
                .build()
        )

        responseObserver.onCompleted()

    }
}