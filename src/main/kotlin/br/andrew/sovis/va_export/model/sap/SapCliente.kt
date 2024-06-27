package br.andrew.sovis.va_export.model.sap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class SapCliente(
        val nome : String,
        val cpfCnpj : String,
        val endereco : Endereco?,
        val idCliente : String){

    var telefone : String? = null
    var email : String? = null
    var idVendedor : String? = null
    var obscadastral : String? = null
    var ierg : String? = null
    var dtnasc : String? = null
    var localidade : Int? = null
}