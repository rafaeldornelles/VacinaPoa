package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
class GrupoVacinacao (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idadeMin: Int,
    @ForeignKey(entity = GrupoPrioritario::class, childColumns = ["grupoPrioritarioId"], parentColumns = ["id"])
    val grupoPrioritarioId: Int?,
    val dataInicioVacinaCao: LocalDate
)