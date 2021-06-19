package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity
class Vacina (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fabricante: String,
    val quantidadeDoses: Int,
    val intervaloDoses: Duration
)