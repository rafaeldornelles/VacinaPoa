package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.UnidadeSaude

@Dao
interface UnidadeSaudeDao: BaseDao<UnidadeSaude> {
    @Query("SELECT * FROM UnidadeSaude")
    fun getUnidadesSaude(): LiveData<List<UnidadeSaude>>

    @Query("SELECT * FROM UnidadeSaude WHERE id = :id")
    fun getUnidadeSaudeById(id: Int): LiveData<UnidadeSaude>
}