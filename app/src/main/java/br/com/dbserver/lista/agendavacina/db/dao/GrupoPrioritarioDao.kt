package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario

@Dao
interface GrupoPrioritarioDao: BaseDao<GrupoPrioritario> {
    @Query("SELECT * FROM grupoprioritario")
    fun getGruposPrioritarios(): LiveData<List<GrupoPrioritario>>
    
    @Query("SELECT * FROM grupoprioritario WHERE id = :id")
    fun getGrupoPrioritarioById(id:Int): LiveData<GrupoPrioritario>
}