package br.com.dbserver.lista.agendavacina.model.populated

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import br.com.dbserver.lista.agendavacina.model.*
import java.io.Serializable

class UsuarioPopulado (
    @Embedded
    var usuario: Usuario?,
    @Relation(
        entityColumn = "id",
        parentColumn = "idUnidadeSaude"
    )
    var unidadeSaude: UnidadeSaude?,
    @Relation(
        entityColumn = "id",
        parentColumn = "id",
        associateBy = Junction(UsuarioComorbidade::class, parentColumn = "usuarioId", entityColumn = "comorbidadeId")
    )
    var comorbidades:List<Comorbidade>?,
    @Relation(
        entityColumn = "id",
        parentColumn = "idGrupoVacinacao"
    )
    var grupoVacinac: GrupoVacinacao?,
    @Relation(
        entityColumn = "usuarioId",
        parentColumn = "id"
    )
    var agendamentos: List<Agendamento>?,
    @Relation(
        entityColumn = "pacienteId",
        parentColumn = "id"
    )
    var vacinacao: Vacinacao?,
    @Relation(
        entityColumn = "usuarioId",
        parentColumn = "id"
    )
    var usaruarioComorbidades: List<UsuarioComorbidade>
): Serializable