package br.com.dbserver.lista.agendavacina.model

import java.time.LocalDate

class PublicoGeral (
    id: String,
    nome: String,
    senha: String,
    email: String,
    val cpf: String,
    val dataNascimento: LocalDate
) : Usuario(id, nome, senha, email)