package br.andrew.sovis.va_export.repositorys

import br.andrew.sovis.va_export.model.Cliente
import br.andrew.sovis.va_export.model.Pedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface ClienteRepository : PagingAndSortingRepository<Cliente, Long>, JpaRepository<Cliente, Long> {

    @Query("SELECT e FROM Cliente e WHERE e.retorno IS NULL OR (e.retorno < 0 AND e.retorno > -3)")
    abstract fun findByIdRetornoIsNullOrIdLessThan() : List<Cliente>
}