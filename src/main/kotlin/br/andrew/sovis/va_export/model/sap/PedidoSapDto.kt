package br.andrew.sovis.va_export.model.sap

import br.andrew.sovis.va_export.model.Pedido
import com.fasterxml.jackson.annotation.*
import java.text.SimpleDateFormat
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class PedidoSapDto(
    val idCliente : String,
    val idEmpresa : String,
    val idFormaPagamento: String,
    val idCondicaoPagamento : String,
    @JsonFormat(with = arrayOf(JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
    val produtos : List<Produto>,
    var idPedido : String,
    val codVendedor : String?) {


    //TODO fazer parse
    constructor(pedido: Pedido) : this(
        pedido.idClienteErp ?: throw Exception("Cod Cliente nao informado"),
        pedido.idEmpresaErp ?: throw Exception("Id da Filial nao informado"),
        pedido.idFormaPagtoErp ?: throw Exception("Forma Pagamento nao informado"),
        pedido.idPrazoPagtoErp  ?: throw Exception("Condicao pagamento nao informado"),
        pedido.itensPedido?.map { Produto(it,pedido.idTabPrecoErp) } ?: listOf(),
        pedido.id.toString(),
        pedido.idUsuarioErp
        )

    var frete: Double? = null
    var observacao : String? = null
    var precoBase : Int? = null
    var endereco : String? = null
    var uuid : String? = null

    //TODO fazer parse de data
    var dataEntraga : String? = SimpleDateFormat("yyy-MM-dd").format(Date())
        set(value)  {
            if(value != null)
                field = value
        }
    var tipoPedido : Int = 9
    var desconto : Double = 0.0

    @JsonIgnore
    fun getEnderecoEntrega(): EnderecoId {
        return EnderecoId(endereco!!)
    }


}

