package br.com.dbserver.lista.agendavacina.model

import java.time.Duration

class Vacina (
    val id: Int,
    val fabricante: String,
    val quantidadeDoses: Int,
    val intervaloDoses: Duration
)