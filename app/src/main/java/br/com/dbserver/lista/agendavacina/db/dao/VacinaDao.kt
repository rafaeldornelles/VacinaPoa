package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.Vacina

@Dao
interface VacinaDao: BaseDao<Vacina> {
    @Query("SELECT * FROM vacina")
    fun getVacinas(): LiveData<List<Vacina>>

    @Query("SELECT * FROM vacina WHERE id = :id")
    fun getVacinaById(id: Int): LiveData<Vacina>
}