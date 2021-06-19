package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["usuarioId", "comorbidadeId"])
class UsuarioComorbidade (
        val id: Int?,
        @ForeignKey(entity = Usuario::class, childColumns = ["usuarioId"], parentColumns = ["id"])
        val usuarioId: String,
        @ForeignKey(entity = Comorbidade::class, childColumns = ["comorbidadeId"], parentColumns = ["id"])
        val comorbidadeId: Int

)