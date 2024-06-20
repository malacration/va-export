package br.andrew.sovis.va_export.repositorys

import br.andrew.sovis.va_export.model.Pedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface PedidoRepository : PagingAndSortingRepository<Pedido, Long>, JpaRepository<Pedido, Long> {

    @Query("SELECT e FROM Pedido e WHERE e.idPedidoErp IS NULL OR (CAST(e.idPedidoErp AS integer) < 0 AND CAST(e.idPedidoErp AS integer) > -3)")
    abstract fun findByIdPedidoErpIsNullOrIdLessThan() : List<Pedido>
}