package br.andrew.sovis.va_export.schedules


import br.andrew.sovis.va_export.controllers.IndexController
import br.andrew.sovis.va_export.repositorys.PedidoRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class PedidoScheduled(
    val controller : IndexController,
    val pedidoRepository: PedidoRepository) {

    val logger: Logger = LoggerFactory.getLogger(PedidoScheduled::class.java)


    @Scheduled(fixedDelay = 3000)
    fun execute() {
        val resultado = pedidoRepository.findByIdPedidoErpIsNullOrIdLessThan()
        resultado.forEach {
            try {
                controller.enviar(it)
            }catch (e : Exception){
                logger.error("Erro ao importar pedido do VA",e)
                pedidoRepository.save(it.errorAoEnviar())
            }
        }
    }
}