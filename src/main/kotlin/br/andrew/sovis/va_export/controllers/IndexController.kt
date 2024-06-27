package br.andrew.sovis.va_export.controllers

import br.andrew.sovis.va_export.model.Cliente
import br.andrew.sovis.va_export.model.Pedido
import br.andrew.sovis.va_export.model.sap.PedidoRetorno
import br.andrew.sovis.va_export.repositorys.ClienteRepository
import br.andrew.sovis.va_export.repositorys.PedidoRepository
import br.andrew.sovis.va_export.service.SapService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class IndexController(
    val repo : PedidoRepository,
    val clienteRepository: ClienteRepository,
    val sapService: SapService
) {

    @GetMapping()
    fun index(page : Pageable): Page<Pedido> {
        return repo.findAll(page)
    }

    @GetMapping("/enviar/{id}")
    fun enviar(@PathVariable id : Long): PedidoRetorno {
        val pedido = repo.findById(id).orElseThrow{ Exception("Nao existe pedido com esse ID - $id")}
        return enviar(pedido)
    }

    @Transactional
    fun enviar(pedido : Pedido): PedidoRetorno {
        return if(pedido.RetornoSap() == null)
            sapService.enviar(pedido).also {
                repo.save(pedido.marcarEnviado(it))
            }
        else
            pedido.RetornoSap()!!
    }

    @Transactional
    fun enviar(cliente : Cliente): Any {
        return if(cliente.RetornoSap() == null)
            sapService.enviar(cliente).also {
                clienteRepository.save(cliente.marcarEnviado(it))
            }
        else
            cliente.RetornoSap()!!
    }
}