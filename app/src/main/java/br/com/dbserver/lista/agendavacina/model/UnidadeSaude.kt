package br.com.dbserver.lista.agendavacina.model

import java.time.LocalTime

class UnidadeSaude (
    val id: Int,
    val nome: String,
    val endereco: String,
    val horarioAbertura: LocalTime,
    val horarioFechamento: LocalTime,
    val linkGoogleMaps: String?
)