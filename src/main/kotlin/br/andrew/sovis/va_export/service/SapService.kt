package br.andrew.sovis.va_export.service

import br.andrew.sovis.va_export.model.Pedido
import br.andrew.sovis.va_export.model.sap.PedidoSapDto
import br.andrew.sovis.va_export.model.sap.RetornoSap
import br.andrew.sovis.va_export.repositorys.PedidoRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class SapService(
    @Value("\${sap-service.host:http://localhost:8080}") private val host : String,
    private val pedidoRepository: PedidoRepository,
    private val restTemplate: RestTemplate) {

    fun enviar(pedido: Pedido): RetornoSap {
        val pedidoSap = PedidoSapDto(pedido)
        val request = RequestEntity
            .post("$host/quotation")
            .body(pedidoSap)
        return restTemplate.exchange(request, RetornoSap::class.java).body!!
    }
}