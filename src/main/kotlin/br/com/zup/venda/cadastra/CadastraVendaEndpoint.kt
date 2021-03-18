package br.com.zup.venda.cadastra

import br.com.zup.CadastraNovaVendaServiceGrpc
import br.com.zup.NovaVendaRequest
import br.com.zup.NovaVendaResponse
import br.com.zup.estado.EstadoRepository
import br.com.zup.pais.PaisRepository
import br.com.zup.shared.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import io.micronaut.validation.validator.Validator
import javax.inject.Singleton

@Singleton
@ErrorHandler
class CadastraVendaEndpoint(
    val validator: Validator,
    val paisRepository: PaisRepository,
    val estadoRepository: EstadoRepository
) : CadastraNovaVendaServiceGrpc.CadastraNovaVendaServiceImplBase() {

    override fun cadastrar(request: NovaVendaRequest, responseObserver: StreamObserver<NovaVendaResponse>) {

        val novaVenda = request.toModel(validator)

        val pais = paisRepository.findById(novaVenda.paisId)
        val estado = estadoRepository.findById(novaVenda.estadoId)


        responseObserver.onNext(
            NovaVendaResponse.newBuilder()
                .setEmail(novaVenda.email)
                .setNome(novaVenda.nome)
                .setSobrenome(novaVenda.sobrenome)
                .setDocumento(novaVenda.documento)
                .setEndereco(novaVenda.endereco)
                .setComplemento(novaVenda.complemento)
                .setCidade(novaVenda.cidade)
                .setPais(pais.get().nome)
                .setEstado(
                    if(estado.isPresent)
                        estado.get().nome
                    else
                        ""
                )
                .setTelefone(novaVenda.telefone)
                .setCep(novaVenda.cep)
                .build()
        )

        responseObserver.onCompleted()
    }
}