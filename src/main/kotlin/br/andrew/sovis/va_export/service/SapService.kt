package br.andrew.sovis.va_export.service

import br.andrew.sovis.va_export.model.Cliente
import br.andrew.sovis.va_export.model.Pedido
import br.andrew.sovis.va_export.model.sap.ClienteRetorno
import br.andrew.sovis.va_export.model.sap.PedidoSapDto
import br.andrew.sovis.va_export.model.sap.PedidoRetorno
import br.andrew.sovis.va_export.repositorys.PedidoRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class SapService(
    @Value("\${sap-service.host:http://localhost:8080}") private val host : String,
    private val pedidoRepository: PedidoRepository,
    private val restTemplate: RestTemplate) {

    fun enviar(pedido: Pedido): PedidoRetorno {
        val request = RequestEntity
            .post("$host/quotation")
            .body(PedidoSapDto(pedido))
        return restTemplate.exchange(request, PedidoRetorno::class.java).body!!
    }

    fun enviar(cliente: Cliente): ClienteRetorno {
        return if(cliente.idClienteErp == null) {
            restTemplate.exchange(RequestEntity
                .post("$host/business-partners")
                .body(cliente.getSapCliente()), ClienteRetorno::class.java).body!!

        }else{
            restTemplate.exchange(RequestEntity
                .put("$host/business-partners/${cliente.idClienteErp}")
                .body(cliente.getSapCliente()), Void::class.java)
            ClienteRetorno(cliente.idClienteErp!!)

        }
    }
}