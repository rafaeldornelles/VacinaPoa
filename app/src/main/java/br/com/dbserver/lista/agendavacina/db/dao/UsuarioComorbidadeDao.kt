package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.UsuarioComorbidade

@Dao
interface UsuarioComorbidadeDao: BaseDao<UsuarioComorbidade>{
    @Query("SELECT * FROM UsuarioComorbidade WHERE usuarioId = :usuarioId")
    fun findByUsuario(usuarioId: String): LiveData<List<UsuarioComorbidade>>
}