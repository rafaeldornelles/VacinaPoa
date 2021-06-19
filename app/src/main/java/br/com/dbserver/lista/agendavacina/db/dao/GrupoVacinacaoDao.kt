package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.GrupoVacinacao

@Dao
interface GrupoVacinacaoDao: BaseDao<GrupoVacinacao> {
    @Query("SELECT * FROM grupovacinacao")
    fun getGruposVacinacao(): LiveData<List<GrupoVacinacao>>

    @Query("SELECT * FROM grupovacinacao WHERE id = :id")
    fun getGrupoVacinacaoById(id:Int): LiveData<GrupoVacinacao>
}