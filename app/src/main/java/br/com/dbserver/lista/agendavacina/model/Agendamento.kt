package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity
class Agendamento (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ForeignKey(entity = Usuario::class, childColumns = ["usuarioId"], parentColumns = ["id"])
    val usuarioId: String,
    @ForeignKey(entity = UnidadeSaude::class, childColumns = ["unidadeId"], parentColumns = ["id"])
    val unidadeId: Int,
    val data: LocalDate,
    val hora: LocalTime

)