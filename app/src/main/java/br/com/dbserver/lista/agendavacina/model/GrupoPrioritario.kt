package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class GrupoPrioritario (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val descricao: String
): Serializable