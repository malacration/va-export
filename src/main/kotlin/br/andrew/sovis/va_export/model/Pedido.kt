package br.andrew.sovis.va_export.model

import br.andrew.sovis.va_export.model.sap.RetornoSap
import jakarta.persistence.*
import java.sql.Time
import java.util.*


@Entity
@Table(name = "PEDIDO")
class Pedido {

    @Id
    @Column(name = "IDPEDIDO")
    var id: Long? = null

    @Column(name = "IDPEDIDOERP", nullable = false)
    var idPedidoErp: String? = null

    @Column(name = "IDCLIENTEERP", nullable = false)
    var idClienteErp: String? = null

    @Column(name = "IDUSUARIOERP", nullable = false)
    var idUsuarioErp: String? = null

    @Column(name = "IDEMPRESAERP", nullable = false)
    var idEmpresaErp: String? = null

    @Column(name = "IDTIPOPEDIDOERP", nullable = false)
    var idTipoPedidoErp: String? = null

    @Column(name = "IDTABPRECOERP", nullable = false)
    var idTabPrecoErp: String? = null

    @Column(name = "IDFORMAPAGTOERP", nullable = false)
    var idFormaPagtoErp: String? = null

    @Column(name = "IDPRAZOPAGTOERP", nullable = false)
    var idPrazoPagtoErp: String? = null

    @Column(name = "VALORTOTALBRUTO", nullable = false)
    var valorTotalBruto: Double? = null

    @Column(name = "VALORJUROS", nullable = false)
    var valorJuros: Double? = null

    @Column(name = "VALORTOTALBRUTOJUROS", nullable = false)
    var valorTotalBrutoJuros: Double? = null

    @Column(name = "DESCONTOPRATICADO", nullable = false)
    var descontoPraticado: Double? = null

    @Column(name = "VALORFRETE", nullable = false)
    var valorFrete: Double? = null

    @Column(name = "VALORTOTALLIQUIDO", nullable = false)
    var valorTotalLiquido: Double? = null

    @Column(name = "SITUACAO", nullable = false)
    var situacao: Int? = null

    @Column(name = "DATACRIACAO", nullable = false)
    var dataCriacao: Date? = null

    @Column(name = "HORACRIACAO", nullable = false)
    var horaCriacao: Time? = null

    @Column(name = "DATAMODIFICACAO")
    var dataModificacao: Date? = null

    @Column(name = "HORAMODIFICACAO")
    var horaModificacao: Time? = null

    @Column(name = "OBSERVACAO")
    var observacao: String? = null

    @Column(name = "DAV")
    var dav: String? = null

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    var itensPedido: List<ItemPedido>? = null

    fun marcarEnviado(retornoSap: RetornoSap): Pedido {
        idPedidoErp = retornoSap.DocNum
        return this
    }

    fun RetornoSap() : RetornoSap? {
        if(idPedidoErp != null && !hasError())
            return RetornoSap(idPedidoErp!!)
        else
            return null
    }

    fun errorAoEnviar(): Pedido {
        if(idPedidoErp?.toIntOrNull() == null)
            idPedidoErp = "-1"
        else
            idPedidoErp = (idPedidoErp!!.toInt()-1).toString()
        return this
    }

    fun hasError(): Boolean {
        return idPedidoErp?.toIntOrNull() is Int
    }
}