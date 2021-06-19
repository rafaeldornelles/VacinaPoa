package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Comorbidade (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cid: String,
    val descricao: String
) : Serializable