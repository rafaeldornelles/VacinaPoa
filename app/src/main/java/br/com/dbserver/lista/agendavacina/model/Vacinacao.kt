package br.com.dbserver.lista.agendavacina.model

import java.time.LocalDate

class Vacinacao (
    val dataPrimeiraDose: LocalDate,
    val dataSegundaDose: LocalDate,
    val pacienteId: String,
    val profissionalId: String,
    val vacinaId: Int
)