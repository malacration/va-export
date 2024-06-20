package br.andrew.sovis.va_export.model

import jakarta.persistence.*

@Entity
@Table(name = "ITEMPEDIDO")
class ItemPedido {

    @Id
    @Column(name = "IDITEMPEDIDO")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "IDPEDIDO", insertable = false, updatable = false)
    var pedido: Pedido? = null

    @Column(name = "IDTABPRECOERP")
    var idTabPrecoErp: String? = null

    @Column(name = "IDPRODUTOERP")
    var idProdutoErp: String? = null

    @Column(name = "VALORTABPRECO")
    var valorTabPreco: Double? = null

    @Column(name = "VALORPRATICADO")
    var valorPraticado: Double? = null

    @Column(name = "QTDE")
    var qtde: Double? = null

    @Column(name = "VALORTOTALBRUTO")
    var valorTotalBruto: Double? = null

    @Column(name = "DESCONTOPRATICADO")
    var descontoPraticado: Double? = null

    @Column(name = "VALORTOTALLIQUIDO")
    var valorTotalLiquido: Double? = null

    @Column(name = "SITUACAO")
    var situacao: Int? = null

    @Column(name = "DAV")
    var dav: String? = null
}