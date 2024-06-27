package br.andrew.sovis.va_export

import br.andrew.sovis.va_export.model.Cliente
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class ClienteTest {

    @Test
    fun clienteSapDataNascimento(){
        val cliente = Cliente().also {
            it.nome = "windson"
            it.cnpjCpf = "cpf"
            it.dataNascimento = "05/08/1992"
        }
        val clienteSap = cliente.getSapCliente()
        Assertions.assertEquals("1992-08-05",clienteSap.dtnasc)
    }
}