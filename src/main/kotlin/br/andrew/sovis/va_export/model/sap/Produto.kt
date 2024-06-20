package br.andrew.sovis.va_export.model.sap

import br.andrew.sovis.va_export.model.ItemPedido
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Produto(val idProduto : String,
              val valorTabela : Double,
              val precoUnitario : Double,
              val quantidade : Double,
              var listapreco : Int){

    var desconto : Double = 0.0
    var idItem: String? = null

    constructor(itemPedido: ItemPedido,listaPreco: String?) : this(
        itemPedido.idProdutoErp?: throw Exception("Valor da tabela nao informado"),
        itemPedido.valorTabPreco?: throw Exception("Valor da tabela nao informado"),
        itemPedido.valorPraticado?: throw Exception("Valor da tabela nao informado"),
        itemPedido.qtde?: throw Exception("Sem quantidade informada"),
        listaPreco?.toInt() ?: throw Exception("Sem lista de preco informada")
        )
}