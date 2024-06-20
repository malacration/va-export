package br.andrew.sovis.va_export.model

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "DAVSOVIS")
class Dav {

    @Id
    @Column(name = "IDDAVSOVIS")
    var idDavSovis: Int? = null

    @Column(name = "DATAHORA")
    var dataHora: Timestamp? = null

    @Column(name = "IDUSUARIO")
    var idUsuario: String? = null

    @Column(name = "IDCLIENTE")
    var idCliente: String? = null
}