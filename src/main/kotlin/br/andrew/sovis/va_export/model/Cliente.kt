package br.andrew.sovis.va_export.model

import br.andrew.sovis.va_export.model.sap.ClienteRetorno
import br.andrew.sovis.va_export.model.sap.Endereco
import br.andrew.sovis.va_export.model.sap.SapCliente
import jakarta.persistence.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
@Table(name = "CLIENTE ")
class Cliente {

    @Id
    @Column(name = "IDCLIENTE")
    var id: Long? = null

    @Column(name = "IDCLIENTEERP")
    var idClienteErp: String? = null

    @Column(name = "NOME")
    var nome: String? = null

    @Column(name = "RSOCIAL")
    var rSocial: String? = null

    @Column(name = "CNPJCPF")
    var cnpjCpf: String? = null

    @Column(name = "IERG")
    var ieRg: String? = null

    @Column(name = "DATANASCIMENTO")
    var dataNascimento: String? = null

    @Column(name = "ENDERECO")
    var endereco: String? = null

    @Column(name = "NUMERO")
    var numero: String? = null

    @Column(name = "BAIRRO")
    var bairro: String? = null

    @Column(name = "CIDADE")
    var cidade: String? = null

    @Column(name = "UF")
    var uf: String? = null

    @Column(name = "CEP")
    var cep: String? = null

    @Column(name = "COMPLEMENTO")
    var complemento: String? = null

    @Column(name = "REGIAO")
    var regiao: String? = null

    @Column(name = "ATIVIDADE")
    var atividade: String? = null

    @Column(name = "EXTRA1")
    var extra1: String? = null

    @Column(name = "EXTRA2")
    var extra2: String? = null

    @Column(name = "EXTRA3")
    var extra3: String? = null

    @Column(name = "TELEFONE")
    var telefone: String? = null

    @Column(name = "FAX")
    var fax: String? = null

    @Column(name = "CELULAR")
    var celular: String? = null

    @Column(name = "EMAIL")
    var email: String? = null

    @Column(name = "EMAILNFE")
    var emailNfe: String? = null

    @Column(name = "OBSCADASTRAL")
    var obsCadastral: String? = null

    @Column(name = "OBSFINANCEIRA")
    var obsFinanceira: String? = null

    @Column(name = "DESCMAXIMOPEDIDO")
    var descMaximoPedido: Double? = null

    @Column(name = "CLIENTETIPO")
    var clienteTipo: String? = null

    @Column(name = "RETORNO")
    var retorno: Int? = null

    fun RetornoSap(): ClienteRetorno? {
        if(retorno != null && !hasError())
            return ClienteRetorno("")
        else
            return null
    }

    fun hasError(): Boolean {
        return retorno != null && retorno!! < 0
    }

    fun marcarEnviado(it: ClienteRetorno): Cliente {
        retorno = 1
        return this
    }

    fun errorAoEnviar(): Cliente {
        if(retorno == null)
            retorno = -1
        else
            retorno = retorno!!-1
        return this
    }

    fun converterData(data: String?): String? {
        if(data == null)
            return null
        val formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatoSaida = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val dataConvertida = LocalDate.parse(data, formatoEntrada)
        return dataConvertida.format(formatoSaida)
    }

    fun getSapCliente() : SapCliente{
        val endereco = if(uf != null) {
             Endereco(
                endereco ?: throw Exception("Cliente sem Endereco"),
                numero ?: throw Exception("Cliente sem Numero"),
                bairro ?: throw Exception("Cliente sem bairro"),
                cidade ?: throw Exception("Cliente sem Cidade"),
                uf ?: throw Exception("Cliente sem UR"),
                cep ?: throw Exception("Cliente sem CEP"),
                "BR", "tipoEndereco"
            ).also {
                it.localidade = regiao?.toIntOrNull()
                it.complemento = complemento
            }
        }
        else
            null
        return SapCliente(
            nome ?: throw Exception("Cliente sem Nome"),
            cnpjCpf ?: throw Exception("Cliente sem CPF"),
            endereco,
            id.toString()).also {
                it.telefone = telefone
                it.email = email
                it.obscadastral = obsCadastral
                it.ierg = ieRg
                it.dtnasc = converterData(dataNascimento)
            }
    }
}
