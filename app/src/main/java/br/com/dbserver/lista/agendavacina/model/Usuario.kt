package br.com.dbserver.lista.agendavacina.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
open class Usuario (
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var nome: String,
    @Ignore
    var senha: String,
    var email: String,
    var cpf: String,
    var dataNascimento: LocalDate,
    var identificacaoFuncional: String?,
    @ForeignKey(entity = UnidadeSaude::class, childColumns = ["idUnidadeSaude"], parentColumns = ["id"])
    var idUnidadeSaude: Int?,
    @ForeignKey(entity = GrupoVacinacao::class, childColumns = ["idGrupoVacinacao"], parentColumns = ["id"])
    var idGrupoVacinacao: Int?
){

    constructor(id: String, nome: String, email: String, cpf: String, dataNascimento: LocalDate, identificacaoFuncional: String?, idUnidadeSaude: Int?, idGrupoVacinacao: Int?) :this(id, nome, "", email, cpf, dataNascimento, identificacaoFuncional, idUnidadeSaude, idGrupoVacinacao)
    fun getTipoUsuario():TipoUsuario = if (identificacaoFuncional == null && idUnidadeSaude == null) TipoUsuario.PUBLICO_GERAL else TipoUsuario.PROFISSIONAL_SAUDE

    enum class TipoUsuario{
        PUBLICO_GERAL, PROFISSIONAL_SAUDE
    }

}
