package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.dbserver.lista.agendavacina.model.Vacinacao

@Dao
interface VacinacaoDao: BaseDao<Vacinacao> {
    @Query("SELECT * FROM vacinacao")
    fun getVacinacoes(): LiveData<List<Vacinacao>>

    @Query("SELECT * FROM vacinacao WHERE id = :id")
    fun getVacinacaoById(id: Int): LiveData<Vacinacao>
}