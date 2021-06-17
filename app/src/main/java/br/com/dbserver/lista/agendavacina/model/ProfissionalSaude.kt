package br.com.dbserver.lista.agendavacina.model

import java.time.LocalDate

class ProfissionalSaude (
    id: String,
    nome: String,
    senha: String,
    email: String,
    val identificacaoFuncional: String,
    val unidadeSaudeId: Int
) : Usuario(id, nome, senha, email)