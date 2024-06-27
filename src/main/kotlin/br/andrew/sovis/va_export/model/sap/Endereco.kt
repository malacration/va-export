package br.andrew.sovis.va_export.model.sap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Endereco(val rua : String,
               val numero : String,
               val bairro : String,
               val cidade : String,
               val estado : String,
               val cep : String,
               val pais : String,
               val tipoEndereco : String) {

    var complemento: String? = null
    var localidade: Int? = null

}