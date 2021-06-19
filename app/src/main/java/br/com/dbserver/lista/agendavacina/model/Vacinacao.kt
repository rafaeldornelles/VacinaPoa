package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
class Vacinacao (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dataPrimeiraDose: LocalDate,
    val dataSegundaDose: LocalDate,
    @ForeignKey(entity = Usuario::class, childColumns = ["pacienteId"], parentColumns = ["id"])
    val pacienteId: String,
    @ForeignKey(entity = Usuario::class, childColumns = ["profissionalId"], parentColumns = ["id"])
    val profissionalId: String,
    @ForeignKey(entity = Vacina::class, childColumns = ["vacinaId"], parentColumns = ["id"])
    val vacinaId: Int
)