package br.andrew.sovis.va_export.model

import jakarta.persistence.*

@Entity
@Table(name = "CLIENTE_SOVIS ")
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
}
