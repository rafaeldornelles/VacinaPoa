package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity
class UnidadeSaude (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val endereco: String,
    val horarioAbertura: LocalTime,
    val horarioFechamento: LocalTime,
    val linkGoogleMaps: String?,
    val linkImagem: String,
    val capacidadeVacinacaoHora: Int,
    val isDriveThru: Boolean
)