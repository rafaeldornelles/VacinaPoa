package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.Comorbidade

@Dao
interface ComorbidadeDao: BaseDao<Comorbidade> {
    @Query("SELECT * FROM comorbidade")
    fun getComorbidades(): LiveData<List<Comorbidade>>

    @Query("SELECT * FROM comorbidade WHERE id = :id")
    fun findById(id:Int): LiveData<Comorbidade>
}