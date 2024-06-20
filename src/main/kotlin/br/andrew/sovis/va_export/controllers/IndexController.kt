package br.andrew.sovis.va_export.controllers

import br.andrew.sovis.va_export.model.Pedido
import br.andrew.sovis.va_export.model.sap.RetornoSap
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
    val sapService: SapService
) {

    @GetMapping()
    fun index(page : Pageable): Page<Pedido> {
        return repo.findAll(page)
    }

    @GetMapping("/enviar/{id}")
    fun enviar(@PathVariable id : Long): RetornoSap {
        val pedido = repo.findById(id).orElseThrow{ Exception("Nao existe pedido com esse ID - $id")}
        return enviar(pedido)
    }

    @Transactional
    fun enviar(pedido : Pedido): RetornoSap {
        return if(pedido.RetornoSap() == null)
            sapService.enviar(pedido).also {
                repo.save(pedido.marcarEnviado(it))
            }
        else
            pedido.RetornoSap()!!
    }
}